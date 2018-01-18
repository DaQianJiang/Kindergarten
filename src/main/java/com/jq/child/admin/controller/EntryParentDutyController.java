package com.jq.child.admin.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jq.child.admin.pojo.ParentDuty;
import com.jq.child.admin.pojo.StudentInfo;
import com.jq.child.admin.service.IParentDutyInfoSV;
import com.jq.child.admin.service.IStudentInfoSV;
import com.jq.child.util.JedisUtil;
import com.jq.child.util.OutPutParamUtil;
import com.jq.child.util.TimeUtil;
import com.jq.child.util.constants;

@Controller
public class EntryParentDutyController  {
	
	private static Logger logger = Logger.getLogger(EntryParentDutyController.class);
   
@Resource
    private IParentDutyInfoSV parentdutySV;
@Resource
    private IStudentInfoSV stuSV;
@RequestMapping(value="entryparentduty",produces = "application/json; charset=utf-8")
@ResponseBody
public String addActiviyConfig(String duty_time1, String duty_class1, String duty_childnum1) throws Exception{
	logger.info("插入家长值班活动开始");
	logger.info(duty_time1);
	logger.info(duty_class1);
	logger.info(duty_childnum1);
	boolean flag = false;
	
	Gson gson = new Gson();
	
	List<StudentInfo> list=stuSV.getStuInfoBySno(duty_childnum1);
	ParentDuty value = new ParentDuty();
	value.setDutyParentname(list.get(0).getPname());
	value.setDutyClass(duty_class1);
	//扩展字段作为学生学号
	value.setExt1(duty_childnum1);
	value.setParentdutyTime(TimeUtil.string2Date(duty_time1));
	OutPutParamUtil ret = new OutPutParamUtil();
	
	logger.info("........"+gson.toJson(value));
	try {
		parentdutySV.insertParentduty(value);
		//将家长值班信息放入缓存
		JedisUtil.set("dutyClass"+duty_childnum1, duty_class1);
		JedisUtil.set("dutyTime"+duty_childnum1, duty_time1);
		flag = true;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		logger.error("插入失败..."+e.getMessage(),e);
	}
	if(flag == false){
		ret.setRetCode("00001");
		ret.setRetDesc("失败");
	}else{
		ret.setRetCode("0000");
		ret.setRetDesc("成功");
	}
	return gson.toJson(ret);
}

//查询显示
@RequestMapping(value="selectAllparent_duty",produces = "application/json; charset=utf-8")
@ResponseBody
public String SelectAllParentDutyInfo()
{
	Gson gson = new Gson();
	String str = new String();
	List<ParentDuty> list = parentdutySV.SelectdutyInfo();
	for(int i = 0; i < list.size(); i++){
		list.get(i).setExt3(TimeUtil.date2String(list.get(i).getParentdutyTime()));
	}
	str = gson.toJson(list);
	logger.info("家长值班信息详情"+str);
	return str;
}
//删除
@RequestMapping(value="deleteParent_duty",produces = "application/json; charset=utf-8")
@ResponseBody
public String deleParentDuty(String stusno)
{
	logger.info("学号为"+stusno);
	String str = new String();
	Gson gson = new Gson();
	OutPutParamUtil ret = new OutPutParamUtil();
	try{
		parentdutySV.deleteParentDuty(stusno);
		ret.setRetDesc("删除成功");
	}catch(Exception e){
		ret.setRetDesc("删除失败");
	}
	
	str = gson.toJson(ret);
	
	return str;
}

@RequestMapping(value="updata_parentduty",produces = "application/json; charset=utf-8")
@ResponseBody
public String updateParentduty(String flag,String dutytime,HttpServletRequest request)
{
	
	HttpSession session = request.getSession();
	String str = new String();
	Gson gson = new Gson();
	OutPutParamUtil ret = new OutPutParamUtil();
	String pname = (String)session.getAttribute("parentName");
	Date parentduty_time =TimeUtil.string2Date(dutytime);
	try{
		String cFlag = parentdutySV.getDutyInfo(parentduty_time, pname);
		if(cFlag.equals(constants.ACCOUNT_TYPE.OUTPAY)){
			ret.setRetCode("00001");
			ret.setRetDesc("不能重复签到");
		}else{
			parentdutySV.updateParentDuty(flag,parentduty_time,pname);
			ret.setRetCode("00000");
			ret.setRetDesc("成功");
		}
	}catch(Exception e){
		logger.info("更新家长值班flag失败");
		ret.setRetCode("00001");
		ret.setRetDesc("失败");
	}
	str = gson.toJson(ret);
	return str;
}

}
