package com.jq.child.student.service;

import java.util.List;

import com.jq.child.student.pojo.ClassPayout;

public interface IClassPayoutSV {
	public void insertPayOutInfo(ClassPayout value)throws Exception;
	
	public List<ClassPayout> getInCoinByClassNum(String calssNum)throws Exception;

	public int delPayOutInfo(int payOutId)throws Exception;
}
