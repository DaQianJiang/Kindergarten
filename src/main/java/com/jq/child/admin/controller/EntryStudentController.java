package com.jq.child.admin.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.jq.child.admin.pojo.StudentInfo;
import com.jq.child.admin.service.IChildRemarksSV;
import com.jq.child.admin.service.IStudentInfoSV;
import com.jq.child.util.OutPutParamUtil;

@Controller
public class EntryStudentController {

	private static Logger logger = Logger.getLogger(EntryStudentController.class);
	
	@Resource
	private IStudentInfoSV stuSV;
	@Resource 
	private IChildRemarksSV childremarksSV;
	
	@RequestMapping("/entrystudent")
	public ModelAndView entryStudentInfo(@RequestParam(value = "student_picture", required = false) MultipartFile file, 
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		StudentInfo stuInfo = new StudentInfo();
		
		
		logger.debug("��ʼ......");
		request.setCharacterEncoding("utf-8");
		ModelAndView mv = new ModelAndView();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		String name = multipartRequest.getParameter("student_name");
		String sno = multipartRequest.getParameter("student_number");
		String age = multipartRequest.getParameter("student_age");
		String sex = multipartRequest.getParameter("student_sex");
		String classNum = multipartRequest.getParameter("student_class");
		String pphone = multipartRequest.getParameter("parent_phone");
		String adress = multipartRequest.getParameter("student_adress");
		String pname = multipartRequest.getParameter("parent_name");
		//String path = request.getSession().getServletContext().getRealPath("entrystudent");
		String path = "E:\\photo\\entrystudent\\";
		String fileName = sno+"_"+file.getOriginalFilename();
		stuInfo.setName(name);
		stuInfo.setSno(sno);
		stuInfo.setAge(Integer.valueOf(age));
		stuInfo.setAdress(adress);
		stuInfo.setClassNum(classNum);
		stuInfo.setImg(fileName);
		stuInfo.setParentPhone(pphone);
		stuInfo.setPname(pname);
		stuInfo.setSex(sex);
		File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }   
        //����  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        
        try {
			if(stuSV.saveStuInfo(stuInfo)){
				mv.addObject("msg", "�ɹ�");
				mv.setViewName("redirect:/YouErYuan/administor_managestuinfo.html");
			}else{
				mv.addObject("msg", "ʧ��");
				mv.setViewName("redirect:/YouErYuan/administor_enterstuinfo.html");
			}
		} catch (Exception e) {
			logger.error("����ѧ����Ϣ����"+e.getMessage(),e);
		}
		logger.debug("�ļ�·��....."+path);
		return mv;
	}
	
	@RequestMapping(value="qryAllStudent",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getStuList(){
		
		logger.info("��ȡѧ���б�....");
		Gson gson = new Gson();
		String str = new String();
		try {
			List<StudentInfo> list = stuSV.getStuInfoBySno(null);
			for(int i = 0; i < list.size(); i++){
				StringBuffer param = new StringBuffer();
				param.append("<").append("img src=\"/Kindergarten/YouErYuan/photo/entrystudent/").append(list.get(i).getImg())
				.append("\" height=\"30\" width=\"30\" >");
				list.get(i).setExt1(param.toString());
			}
			str = gson.toJson(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("��ȡѧ���б����...."+e.getMessage(),e);
		}
		logger.info("ѧ����Ϣjosn�ַ�����"+str);
		return str;
	}
	
	@RequestMapping(value="qryOneStudent",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String qryOneStuInfo(String name, String sno, String className){
		logger.info("��ѯѧ����Ϣ��ʼ....");
						
		Gson gson = new Gson();
		String str = new String();
		try {
			List<StudentInfo> list = stuSV.qryOneStuInfo(name, sno, className);
			for(int i = 0; i < list.size(); i++){
				StringBuffer param = new StringBuffer();
				param.append("<").append("img src=\"/Kindergarten/YouErYuan/photo/entrystudent/").append(list.get(i).getImg())
				.append("\" height=\"30\" width=\"30\" >");
				list.get(i).setExt1(param.toString());
			}
			str = gson.toJson(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("��ѯѧ����Ϣ����"+e.getMessage(),e);
		}
		logger.info("ѧ���б�"+str);
		return str;
		
	}
	
	
//�׶�����ѧ����Ϣ��ѯ
	@RequestMapping(value="qryStuInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String qryStuInfo(String classNum, String sname){
		logger.info("��ʦ�����׶���Ϣ��ѯ....");
						
		Gson gson = new Gson();
		String str = new String();
		try {
			List<StudentInfo> list = stuSV.qryStuInfo(classNum, sname);
			str = gson.toJson(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("��ѯѧ����Ϣ����"+e.getMessage(),e);
		}
		logger.info("ѧ������ѧ����Ϣ��"+str);
		return str;
		
	}
	
	
	@RequestMapping(value="delStudentInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String delStuInfo(@RequestParam(required = false, value = "list[]") List<String> list){
		Gson gson = new Gson();
		String str = new String();
		OutPutParamUtil ret = new OutPutParamUtil();
		try {
			ret = stuSV.delStudentInfo(list);
			str = gson.toJson(ret);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("ɾ��ѧ����Ϣ����"+e.getMessage(),e);
		}
		logger.info(str);
		return str;
	}
	

}
