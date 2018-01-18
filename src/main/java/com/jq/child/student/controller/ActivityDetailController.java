package com.jq.child.student.controller;

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
import com.jq.child.admin.pojo.ActivityConfig;
import com.jq.child.admin.service.IActivityConfigSV;
import com.jq.child.student.pojo.ActivityDetail;
import com.jq.child.student.service.IActivityDetailSV;
import com.jq.child.util.JedisUtil;
import com.jq.child.util.OutPutParamUtil;
import com.jq.child.util.TimeUtil;

@Controller
public class ActivityDetailController {

	private static Logger logger = Logger.getLogger(ActivityDetailController.class);
	
	@Resource
	private IActivityDetailSV activityDtlSV;
	@Resource
	private IActivityConfigSV activityConfigSV;

	
	@RequestMapping(value="getActivityInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAllActivityInfo(){
		logger.info("��ѯ���Ϣ��ʼ....");
		Gson gson = new Gson();
		String str = new String();
		try {
			List<ActivityConfig> list = activityConfigSV.qryAllActivityValue(0);
			for(int i = 0; i < list.size(); i++){
				Date time = list.get(i).getStartTime();
				list.get(i).setExt1(TimeUtil.date2String(time));
			}
			str = gson.toJson(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("��ѯ���Ϣ����..."+e.getMessage(),e);
		}
		logger.info("���Ϣ:"+str);
		return str;
	}
	
	@RequestMapping(value="SignUpActivity",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String signUpActivity(String activityId,HttpServletRequest request){
		Gson gson = new Gson();
		String str = new String();
		HttpSession session = request.getSession();
		String sno =(String) session.getAttribute("sno");
		OutPutParamUtil ret = new OutPutParamUtil();
		try {
			List<ActivityConfig> list = activityConfigSV.qryAllActivityValue(Integer.valueOf(activityId));
			if(list.size()==0){
				ret.setRetCode("0001");
				ret.setRetDesc("δ�鵽�˻..");
			}
			List<ActivityDetail> list1 = activityDtlSV.quryActiveInfo(Integer.valueOf(activityId),sno);
			if(list1.size()!=0)
			{
				logger.info("list1����"+list1.size());
				ret.setRetCode("0001");
				ret.setRetDesc("�����ظ�����");
			}
			else{
				Date endTime = list.get(0).getEndTime();
				if(TimeUtil.compareTo(endTime)>=0){
					activityDtlSV.saveActivityDtl(activityId, list.get(0).getTheme(), request);
					//�������ͻʱ����뻺��TimeUtil.string2Date(duty_time1)TimeUtil.date2String()
					JedisUtil.set("activityTime"+sno,TimeUtil.date2String(list.get(0).getStartTime()) );
					JedisUtil.set("activityTheme"+sno,list.get(0).getTheme());
					ret.setRetCode("0000");
					ret.setRetDesc("�����ɹ�..");
				}else{
					ret.setRetCode("0002");
					ret.setRetDesc("�ʧЧ..");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("��ѯ���Ϣ����..."+e.getMessage(),e);
		}
		str = gson.toJson(ret);
		return str;
	}
	
	@RequestMapping(value="activityChart",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getJionActivityDtl(){
		Gson gson = new Gson();
		String str = new String();
		try {
			List<ActivityConfig> list = activityConfigSV.qryAllActivityValue(0);
			for(int i = 0; i < list.size(); i++){
				StringBuffer param = new StringBuffer();
				List<ActivityDetail> list1 = activityDtlSV.qryActivityDtl(list.get(i).getActivityId());
				for(int j = 0; j < list1.size(); j++){
					param.append(list1.get(j).getPname());
					param.append(" ");
				}
				list.get(i).setExt1(param.toString());
				list.get(i).setExt2(Integer.toString(list1.size()));
				list.get(i).setExt3(TimeUtil.date2String(list.get(i).getStartTime()));
			}
			str = gson.toJson(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("��ѯ�μӻ��ϸ����"+e.getMessage(),e);
		}
		logger.info("���ز�����"+str);
		return str;
	}
	
	
}
