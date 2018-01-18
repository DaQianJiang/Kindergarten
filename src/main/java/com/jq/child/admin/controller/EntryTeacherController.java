package com.jq.child.admin.controller;

import java.io.File;
import java.io.PrintWriter;
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
import com.jq.child.admin.pojo.TeacherInfo;
import com.jq.child.admin.service.ITeacherInfoSV;
import com.jq.child.util.OutPutParamUtil;

@Controller
public class EntryTeacherController {

	private static Logger logger = Logger.getLogger(EntryTeacherController.class);
	
	@Resource
    private ITeacherInfoSV teacherSV;

	@RequestMapping("entryTeacherInfo")
    public ModelAndView entryTeacherInfo(@RequestParam(value ="teacher_picture",required = false )
    MultipartFile file,HttpServletRequest request,HttpServletResponse response )
    throws UnsupportedEncodingException {
	TeacherInfo teacherinfo = new TeacherInfo();
	logger.debug("开始学生信息插入.....");
	request.setCharacterEncoding("utf-8");
	ModelAndView mv = new ModelAndView();
	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	String t_name = multipartRequest.getParameter("teacher_name");
	
	logger.info(t_name);
	String t_number = multipartRequest.getParameter("teacher_number");
	logger.info(t_number);
	String t_age = multipartRequest.getParameter("teacher_age");
	String t_sex = multipartRequest.getParameter("teacher_sex");
	String t_phone = multipartRequest.getParameter("teacher_phone");
	String t_diploma = multipartRequest.getParameter("teacher_diploma");
	String t_adress = multipartRequest.getParameter("teacher_adress");
	String t_class = multipartRequest.getParameter("teacher_class");
	String path = "E:\\photo\\entryTeacherInfo\\";
	//String path = request.getSession().getServletContext().getRealPath("entryteacher");
	String fileName = t_number+"_"+file.getOriginalFilename();
	teacherinfo.setTeacherName(t_name);
	teacherinfo.setTeacherNumber(t_number);
	teacherinfo.setAge(Integer.valueOf(t_age));
	teacherinfo.setSex(t_sex);
    teacherinfo.setPhone(t_phone);
    teacherinfo.setEducation(t_diploma);
    teacherinfo.setAdress(t_adress);
    teacherinfo.setLeasonName(t_class);
    teacherinfo.setImages(fileName);
    
    File targetFile = new File(path, fileName); 
    if(!targetFile.exists()){  
        targetFile.mkdirs();  
    }   
    //保存  
    try {  
        file.transferTo(targetFile);  
    } catch (Exception e) {  
        e.printStackTrace();  
    } 
    try{
    	PrintWriter out = response.getWriter();
		if(teacherSV.saveTeacherInfo(teacherinfo)){
			mv.addObject("msg", "成功");
			mv.setViewName("redirect:YouErYuan/administor_manageteacherinfo.html");
			/*out.print("<script>alert('添加成功');location='YouErYuan/teacherinfo_manage.html';</script>");
			out.close();*/
		}else{
/*			out.print("<script>alert('添加失败');location='YouErYuan/teachinfoInsert.html';</script>");
			out.close();*/
			mv.addObject("msg", "失败");
			mv.setViewName("YouErYuan/administor_enterteacherinfo.html");
		}
    	
    }catch(Exception e){
    	//e.printStackTrace();
    	logger.error("保存学生信息出错："+e.getMessage(),e);
    }
    logger.debug("文件路径....."+path);
	return mv;
}

//删除教师信息
@RequestMapping(value="delTeachertInfo",produces = "application/json; charset=utf-8")
@ResponseBody
public String delTeacherInfo(@RequestParam(required = false, value = "list[]") List<String> list){
	Gson gson = new Gson();
	String str = new String();
	OutPutParamUtil ret = new OutPutParamUtil();
	try {
		ret = teacherSV.delTeacherInfo(list);
		str = gson.toJson(ret);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		logger.error("删除教师信息出错："+e.getMessage(),e);
	}
	logger.info(str);
	return str;
}

//获取教师信息
@RequestMapping(value="qryAllTeacher",produces = "application/json; charset=utf-8")
@ResponseBody
public String getTeacherList(){
	
	logger.info("获取教师列表....");
	Gson gson = new Gson();
	String str = new String();
	try {
		List<TeacherInfo> list = teacherSV.getTeacherInfoBySno(null);
		for(int i = 0; i < list.size(); i++){
			StringBuffer param = new StringBuffer();
			param.append("<").append("img src=\"/Kindergarten/YouErYuan/photo/entryTeacherInfo/").append(list.get(i).getImages())
			.append("\" height=\"30\" width=\"30\" >");
			list.get(i).setExt1(param.toString());
		}
		str = gson.toJson(list);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		logger.error("获取教师列表出错...."+e.getMessage(),e);
	}
	logger.info("教师信息josn字符串："+str);
	return str;
}

//查询学生信息
@RequestMapping(value="qryOneTeacher",produces = "application/json; charset=utf-8")
@ResponseBody
public String qryOneTeacherInfo(String teacherName ,String teacherNumber, String leasonName){
	logger.info("查询教师开始");
	logger.info(teacherName);
	logger.info(teacherNumber);
	logger.info(leasonName);
	
	
	Gson gson = new Gson();
	String str = new String();
	try {
		List<TeacherInfo> list = teacherSV.qryOneTeacherInfo(teacherName,teacherNumber,leasonName);
		for(int i = 0; i < list.size(); i++){
			StringBuffer param = new StringBuffer();
			param.append("<").append("img src=\"/Kindergarten/YouErYuan/photo/entryTeacherInfo/").append(list.get(i).getImages())
			.append("\" height=\"30\" width=\"30\" >");
			list.get(i).setExt1(param.toString());
		}
		str = gson.toJson(list);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		logger.error("获取教师列表出错...."+e.getMessage(),e);
	}
	logger.info("教室信息josn字符串："+str);
	return str;
	
}

}
