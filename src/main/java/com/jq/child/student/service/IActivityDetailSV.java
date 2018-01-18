package com.jq.child.student.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jq.child.admin.pojo.ActivityConfig;
import com.jq.child.student.pojo.ActivityDetail;

public interface IActivityDetailSV {
	public void saveActivityDtl(String activityId, String theme,HttpServletRequest request)throws Exception;

	public List<ActivityDetail> qryActivityDtl(int activityId)throws Exception;

	public List<ActivityDetail> quryActiveInfo(Integer valueOf, String sno);
}
