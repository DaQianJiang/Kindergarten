package com.jq.child.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jq.child.admin.dao.ActivityConfigMapper;
import com.jq.child.admin.pojo.ActivityConfig;
import com.jq.child.admin.pojo.ActivityConfigExample;
import com.jq.child.admin.pojo.ActivityConfigExample.Criteria;
import com.jq.child.admin.service.IActivityConfigSV;
import com.jq.child.util.TimeUtil;

@Service("activityConfigSV")
public class ActivityConfigSVImpl implements IActivityConfigSV {

	@Resource
	private ActivityConfigMapper activityConfigMapper;
	
	public List<ActivityConfig> qryAllActivityValue(int activityId) throws Exception {
		// TODO Auto-generated method stub
		ActivityConfigExample example = new ActivityConfigExample();
		if(activityId>0){
			Criteria criteria = example.createCriteria();
			criteria.andActivityIdEqualTo(activityId);
		}
		List<ActivityConfig> list = activityConfigMapper.selectByExample(example);
		return list;
	}
	public void insertActivity(ActivityConfig value) throws Exception {
		// TODO Auto-generated method stub
		value.setEndTime(TimeUtil.getDateAfter(value.getStartTime(),1));
		activityConfigMapper.insert(value);
	}
	public void delActivity(int activityId) throws Exception {
		// TODO Auto-generated method stub
		activityConfigMapper.deleteByPrimaryKey(activityId);
	}
	
}
