package com.jq.child.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jq.child.admin.pojo.ParentAuthority;
import com.jq.child.admin.service.IParentAuthoritySV;
import com.jq.child.admin.service.IStudentInfoSV;
import com.jq.child.util.OutPutParamUtil;
import com.jq.child.util.TimeUtil;

@Controller
public class AuthorityManageController {
	
	private static Logger logger = Logger.getLogger(AuthorityManageController.class);
	
	@Resource
	private IParentAuthoritySV authoritySV;
	
	@Resource
	private IStudentInfoSV stuSV;
	
	/**
	 * 赋予权限
	 */
	@RequestMapping(value="giveAuthorty",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String giveParentAuthorty(String classNum, String sno,String pname,
			HttpServletRequest request){
		Gson gson = new Gson();
		String str = new String();
		OutPutParamUtil ret = new OutPutParamUtil();
		HttpSession session = request.getSession();
		ParentAuthority bean = new ParentAuthority();

		String tname = (String)session.getAttribute("teacherName");
		
		if(tname==null){
			ret.setRetCode("0006");
			ret.setRetDesc("老师未登录");
		}else{
			bean.setAuthority("1");//赋予权限
			bean.setOptTime(TimeUtil.getNowDate());
			bean.setSno(sno);
			bean.setPname(pname);
			bean.setTname(tname);
			bean.setExt1(classNum);
			try {
				List<ParentAuthority> list = authoritySV.qryAuthorBySno(sno);
				if(list.size()==0){
					authoritySV.insertAuthor(bean);
					ret.setRetCode("0000");
					ret.setRetDesc("权限分配成功");
				}else{
					ret.setRetCode("0002");
					ret.setRetDesc("该家长已经赋予权限");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				ret.setRetCode("0001");
				ret.setRetDesc("查询出错");
			}
		}
		str = gson.toJson(ret);
		return str;
	}
	
	@RequestMapping(value="delAuthorty",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String delAuthortyInfo(String sno){
		Gson gson = new Gson();
		String str = new String();
		OutPutParamUtil ret = new OutPutParamUtil();
		try {
			authoritySV.delAuthorBySno(sno);
			ret.setRetCode("0001");
			ret.setRetDesc("删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ret.setRetCode("0001");
			ret.setRetDesc("删除失败");
		}
		str = gson.toJson(ret);
		return str;
	}
	
	@RequestMapping(value="getAuthorty",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAuthortyInfo(String classNum){
		Gson gson = new Gson();
		String str = new String();
		
		try {
			List<ParentAuthority> list = authoritySV.qryAuthorByClass(classNum);
			str = gson.toJson(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("查询出错"+e.getMessage(),e);
		}
				
		return str;	
	}
}
