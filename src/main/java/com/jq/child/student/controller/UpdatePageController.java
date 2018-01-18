package com.jq.child.student.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jq.child.admin.pojo.StudentInfo;
import com.jq.child.admin.service.IStudentInfoSV;
import com.jq.child.student.pojo.PersonPage;
import com.jq.child.util.JedisUtil;

@Controller
public class UpdatePageController {
	
	private static Logger logger = Logger.getLogger(UpdatePageController.class);
	
	@Resource
	private IStudentInfoSV stuSV;
	
	@RequestMapping(value="updatePersonPage",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updatePersonPage(HttpServletRequest request){
		Gson gson = new Gson();
		String str = new String();
		logger.info("首页开始");
		PersonPage bean = new PersonPage();
		HttpSession session = request.getSession();
		
		String sno = (String) session.getAttribute("sno");
		
		try {
			List<StudentInfo> stuList = stuSV.getStuInfoBySno(sno);
			if(stuList.size()==1){
				bean.setActivityTheme(JedisUtil.get("activityTheme"+sno));
				bean.setActivityTime(JedisUtil.get("activityTime"+sno));
				bean.setDutyClass(JedisUtil.get("dutyClass"+sno));
				bean.setDutyTime(JedisUtil.get("dutyTime"+sno));
				bean.setChildRemarks(JedisUtil.get("totalRemark"+sno));
				bean.setAge(Integer.toString(stuList.get(0).getAge()));
				bean.setClassNum(stuList.get(0).getClassNum());
				
				bean.setImg(stuList.get(0).getImg());
				bean.setName(stuList.get(0).getName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("查询信息出错！！"+e.getMessage(),e);
		}
		str = gson.toJson(bean);
		logger.info("首页信息为："+str);
		return str;
	}
}
