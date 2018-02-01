package com.jq.child.student.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.jq.child.student.dao.ActivityDetailMapper;
import com.jq.child.student.pojo.ActivityDetail;
import com.jq.child.student.pojo.ActivityDetailExample;
import com.jq.child.student.pojo.ActivityDetailExample.Criteria;
import com.jq.child.student.service.IActivityDetailSV;
import com.jq.child.util.TimeUtil;

@Service("activityDtlSV")
public class ActivityDetailSVImpl implements IActivityDetailSV {
	private static Logger logger = Logger.getLogger(ActivityDetailSVImpl.class);
	
	@Resource
	private ActivityDetailMapper activityDetailMapper;
	public void saveActivityDtl(String activityId, String theme,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		ActivityDetail dtl = new ActivityDetail();
		Gson gson = new Gson();
		HttpSession session = request.getSession();
		String pname = (String) session.getAttribute("parentName");
		String sno = (String) session.getAttribute("sno");
		dtl.setActivityId(Integer.valueOf(activityId));
		dtl.setJoinTime(TimeUtil.getNowDate());
		dtl.setPname(pname);
		dtl.setSno(sno);
		dtl.setTheme(theme);
		logger.info("≤Â»Î£∫"+gson.toJson(dtl));
		activityDetailMapper.insertSelective(dtl);
	}
	public List<ActivityDetail> qryActivityDtl(int activityId) throws Exception {
		// TODO Auto-generated method stub
		ActivityDetailExample example = new ActivityDetailExample();
		if(activityId != 0){
			Criteria criteria = example.createCriteria();
			criteria.andActivityIdEqualTo(activityId);
		}
		
		List<ActivityDetail> list = activityDetailMapper.selectByExample(example);
		return list;
	}
	
	public List<ActivityDetail> quryActiveInfo(Integer activityId, String sno) {
	// TODO Auto-generated method stub
	ActivityDetailExample example = new ActivityDetailExample();
	if(activityId != 0){
		Criteria criteria = example.createCriteria();
		criteria.andActivityIdEqualTo(activityId);
		criteria.andSnoEqualTo(sno);
	}
	
	List<ActivityDetail> list = activityDetailMapper.selectByExample(example);
	return list;
}

}
