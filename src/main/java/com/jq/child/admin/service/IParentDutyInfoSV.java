package com.jq.child.admin.service;

import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

import com.jq.child.admin.pojo.ParentDuty;

public interface IParentDutyInfoSV {

	public void insertParentduty(ParentDuty parentduty)throws Exception;

	public List<ParentDuty> SelectdutyInfo();

	public void deleteParentDuty(String stusno);

	public void updateParentDuty(String flag, Date parentduty_time, String pname);

	public String getDutyInfo(Date parentduty_time, String pname)throws Exception;
}
