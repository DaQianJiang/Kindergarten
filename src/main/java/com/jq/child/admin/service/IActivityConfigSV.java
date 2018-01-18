package com.jq.child.admin.service;

import java.util.List;

import com.jq.child.admin.pojo.ActivityConfig;

public interface IActivityConfigSV {
	
	public List<ActivityConfig> qryAllActivityValue(int activityId)throws Exception;
	
	public void insertActivity(ActivityConfig value)throws Exception;
	
	public void delActivity(int activityId)throws Exception;
}
