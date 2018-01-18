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
		logger.info("�׶�������ʱ����ʼ");
		try{
			stuSV.updateFeatureFlage();
		}catch(Exception e){
			logger.error("�׶�������Ϣ���Ĵ���..."+e.getMessage(),e);
		}
		logger.info("����״̬����������...");
		
	}
}
