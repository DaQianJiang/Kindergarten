package com.jq.child.admin.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

import org.springframework.stereotype.Service;

import com.jq.child.admin.dao.ParentDutyMapper;
import com.jq.child.admin.pojo.ParentDuty;
import com.jq.child.admin.pojo.ParentDutyExample;
import com.jq.child.admin.pojo.ParentDutyExample.Criteria;
import com.jq.child.admin.service.IParentDutyInfoSV;
@Service("parentdutySV")
public class ParentDutyInfoSVImpl implements IParentDutyInfoSV{

@Resource 
private ParentDutyMapper parentdutymapper;
	public void insertParentduty(ParentDuty parentduty) {
		// TODO Auto-generated method stub
		
		parentdutymapper.insert(parentduty);
	}
	public List<ParentDuty> SelectdutyInfo() {
		// TODO Auto-generated method stub
		ParentDutyExample example = new ParentDutyExample();
		List<ParentDuty> list =  parentdutymapper.selectByExample(example);
		return list;
	}
	public void deleteParentDuty(String stusno) {
		// TODO Auto-generated method stub
		ParentDutyExample example = new ParentDutyExample();
		Criteria criteria = example.createCriteria();
		criteria.andExt1EqualTo(stusno);
		parentdutymapper.deleteByExample(example);
		
	}
	public void updateParentDuty(String flag, Date dutytime, String pname) {
		// TODO Auto-generated method stub
		
		ParentDutyExample example = new ParentDutyExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentdutyTimeEqualTo(dutytime);
		criteria.andDutyParentnameEqualTo(pname);
		List<ParentDuty> list = parentdutymapper.selectByExample(example);
		list.get(0).setExt2(flag);
		parentdutymapper.updateByExampleSelective(list.get(0), example);
	}
	public String getDutyInfo(Date parentduty_time, String pname) throws Exception {
		// TODO Auto-generated method stub
		ParentDutyExample example = new ParentDutyExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentdutyTimeEqualTo(parentduty_time);
		criteria.andDutyParentnameEqualTo(pname);
		String flag = "0";
		List<ParentDuty> list = parentdutymapper.selectByExample(example);
		if(list.size() == 1){
			flag = list.get(0).getExt2();
		}
		return flag;
	}


}
