package com.jq.child.task;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jq.child.admin.service.IChildRemarksSV;
import com.jq.child.admin.service.IStudentInfoSV;

@Component
public class SetFeatureFlage {
	private static Logger logger = Logger.getLogger(SetFeatureFlage.class);
@Resource 
private IStudentInfoSV stuSV;
@Resource
private IChildRemarksSV childRemarkSV;

@Scheduled(cron = "0/100 * * * * ?")
	public void setFeatureFlage()
	{
		logger.info("幼儿点评定时任务开始");
		try{
			stuSV.updateFeatureFlage();
		}catch(Exception e){
			logger.error("幼儿点评信息更改错误..."+e.getMessage(),e);
		}
		logger.info("更改状态这次任务结束...");
		
	}
}
