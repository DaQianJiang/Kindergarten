package com.jq.child.admin.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jq.child.admin.pojo.ActivityConfig;
import com.jq.child.admin.service.IActivityConfigSV;
import com.jq.child.util.OutPutParamUtil;
import com.jq.child.util.TimeUtil;
@Controller
public class activityConfigContoller {
	
	private static Logger logger = Logger.getLogger(activityConfigContoller.class);
	
	@Resource
	private IActivityConfigSV activityConfigSV;
	
	@RequestMapping(value="addActivityConfig",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addActiviyConfig(String theme, String startTime, String content){
		logger.info("插入新活动开始....");
		boolean flag = false;
		Gson gson = new Gson();
		ActivityConfig value = new ActivityConfig();
		value.setContent(content);
		value.setTheme(theme);
		value.setStartTime(TimeUtil.string2Date(startTime));
		OutPutParamUtil ret = new OutPutParamUtil();
		logger.info("........"+gson.toJson(value));
		try {
			activityConfigSV.insertActivity(value);
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("插入新活动出错..."+e.getMessage(),e);
		}
		if(flag == false){
			ret.setRetCode("00001");
			ret.setRetDesc("失败！！！");
		}else{
			ret.setRetCode("0000");
			ret.setRetDesc("成功！！！");
		}
		return gson.toJson(ret);
	}
	
	@RequestMapping(value="delActivityConfig",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String delActivityConfig(String activityId){
		logger.info("删除活动配置Id为:"+activityId);
		Gson gson = new Gson();
		String str = new String();
		OutPutParamUtil ret = new OutPutParamUtil();
		try {
			activityConfigSV.delActivity(Integer.valueOf(activityId));
			ret.setRetDesc("删除成功");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			ret.setRetDesc("删除失败");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			ret.setRetDesc("删除失败");
		}
		str=gson.toJson(ret);
		return str;
	}
	
}
