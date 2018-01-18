package com.jq.child.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.jq.child.admin.pojo.ParentAuthority;
import com.jq.child.admin.service.IParentAuthoritySV;
import com.jq.child.admin.service.IUserLoginSV;
import com.jq.child.util.OutPutParamUtil;
import com.jq.child.util.constants;

@Controller
public class UserLoginController {
	
	private static Logger logger = Logger.getLogger(UserLoginController.class);
	
	@Resource
	private IUserLoginSV userLoginSV;
	@Resource
	private IParentAuthoritySV authoritySV;
	
	/*@RequestMapping("userLogin")*/
	
	@RequestMapping(value="userLogin",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String userLogin(Model model,HttpServletRequest request,HttpServletResponse response){
		String userType = request.getParameter("userType");
		String account = request.getParameter("userAccount");
		String password = request.getParameter("password1");
		Gson gson = new Gson();
		String str = new String();
		try {
			OutPutParamUtil ret = userLoginSV.userLoginAndRegist(request, account, userType, password);
			str = gson.toJson(ret);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("登录失败"+e.getMessage(),e);
		}
		
		return str;
		
	}
	
	@RequestMapping(value="initWelcome",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String initWelcome(HttpServletRequest request){
		logger.info("初始化页面");
		Gson gson = new Gson();
		String str = new String();
		OutPutParamUtil ret = new OutPutParamUtil();
		HttpSession session = request.getSession();
		String tname = (String)session.getAttribute("teacherName");
		String pname = (String)session.getAttribute("parentName");
		if(tname==null){
			ret.setRetCode("您好，"+pname+"家长");
		}
		if(pname==null){
			ret.setRetCode("您好，"+tname+"老师");
		}
		str = gson.toJson(ret);
		return str;
	}
	
}
