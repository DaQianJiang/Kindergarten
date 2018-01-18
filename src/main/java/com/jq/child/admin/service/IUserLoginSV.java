package com.jq.child.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jq.child.admin.pojo.UserLogin;
import com.jq.child.util.OutPutParamUtil;

public interface IUserLoginSV {

	public OutPutParamUtil userLoginAndRegist(HttpServletRequest request, String account,String userType,String password)throws Exception;

	public List<UserLogin> qryUserLoginByAccount(String account)throws Exception;
	
	public void saveUserLoginInfo(UserLogin value)throws Exception;
}
