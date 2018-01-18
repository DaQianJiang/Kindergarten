package com.jq.child.admin.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.jq.child.admin.pojo.TeachPhoto;
import com.jq.child.admin.service.ITeachPhotoSV;
import com.jq.child.util.OutPutParamUtil;
import com.jq.child.util.TimeUtil;

@Controller
public class EntryTeachPhotoController {

	private static Logger logger = Logger.getLogger(EntryTeachPhotoController.class);
	@Resource
	private ITeachPhotoSV teachPhotoSV;
	
	/**
	 * �ϴ�ͼƬ
	 * @param file
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("uploadTeachPhoto")
	public ModelAndView uploadClassPhoto(@RequestParam(value = "study_file", required = false) MultipartFile file, 
			HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mv = new ModelAndView();
		TeachPhoto bean = new TeachPhoto();
		logger.debug("��ʼ......");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String classNum = multipartRequest.getParameter("duty_class");
		String tname = (String)session.getAttribute("teacherName");
		String path = "E:\\photo\\uploadTeachPhoto\\";
		//String path = request.getSession().getServletContext().getRealPath("uploadTeachPhoto");
		String times = Long.toString(System.currentTimeMillis());
		String fileName = times+"_"+file.getOriginalFilename();
		
		logger.info("�ļ�����"+fileName);
		
		bean.setExt1(classNum);
		bean.setImg(fileName);
		bean.setUpTeacher(tname);
		bean.setUpTime(TimeUtil.getNowDate());
		File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }   
        //����  
        try {  
            file.transferTo(targetFile);  
            teachPhotoSV.insertPhotoInfo(bean);
            mv.addObject("massege","�ϴ��ɹ�");
        } catch (Exception e) {  
            //e.printStackTrace();
        	mv.addObject("massege","�ϴ�ʧ��");
        	logger.error("�ļ��ϴ�ʧ��"+e.getMessage(),e);
        }  
        
        mv.setViewName("redirect:/YouErYuan/administor_studyphoto.html");
		return mv;
	}
	
	/**
	 * ���ݰ༶����ѯ��Ƭ
	 * @param classNum
	 * @return
	 */
	@RequestMapping(value="qryPhotoMessage",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getPhotoMessage(String classNum){
		logger.info("��ѯ�༶Ϊ:"+classNum);
		Gson gson = new Gson();
		String str = new String();
		try {
			List<TeachPhoto> list = teachPhotoSV.qryTeachPhotoValue(classNum);
			for(int i = 0; i < list.size(); i++){
				list.get(i).setExt2(TimeUtil.date2String(list.get(i).getUpTime()));
			}
			str = gson.toJson(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("��Ƭ��Ϣ��ѯʧ��"+e.getMessage(),e);
		}
		logger.info("��Ƭ��Ϣ"+str);
		return str;
	}
	
	/**
	 * ����id��ɾ��ͼƬ��Ϣ
	 * @param photoId
	 * @return
	 */
	@RequestMapping(value="delPhotoMsg",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String delPhotoMessage(String photoId){
		logger.info("ɾ����ϢIdΪ:"+photoId);
		Gson gson = new Gson();
		String str = new String();
		OutPutParamUtil ret = new OutPutParamUtil();
		
		try {
			teachPhotoSV.delPhotoById(Integer.valueOf(photoId));
			ret.setRetDesc("ɾ���ɹ�");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			ret.setRetDesc("ɾ��ʧ��");
			logger.error("����ת������"+e.getMessage(),e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			ret.setRetDesc("ɾ��ʧ��");
			logger.error("ɾ������"+e.getMessage(), e);

		}
		str = gson.toJson(ret);
		logger.info(str);
		return str;
	}
}
