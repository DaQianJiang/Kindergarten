package com.jq.child.admin.service;

import java.util.Date;
import java.util.List;

import com.jq.child.admin.pojo.ChildRemarks;

public interface IChildRemarksSV {
	public void insertRemarke(ChildRemarks value)throws Exception;
	
	public void deleteRemarksBefore()throws Exception;
	
	public List<ChildRemarks> qryRemarksBefore(Date time)throws Exception;
	
	public List<ChildRemarks> qryRemarksBetween7(Date time,String sno,int n)throws Exception;

	public List<ChildRemarks> qryRemarksBySnameAndClassNum(String classNum, String sname)throws Exception;
    
	
	public void deleteRemarks(String sno)throws Exception;

	public List<ChildRemarks> qryBefor7days(Date nowTime) throws Exception;
}
