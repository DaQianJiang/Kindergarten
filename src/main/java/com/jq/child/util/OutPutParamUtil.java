package com.jq.child.util;

import java.util.List;

import com.jq.child.student.pojo.ClassIncome;
import com.jq.child.student.pojo.ClassPayout;

public class OutPutParamUtil {
	
	private String retCode;
	
	private String retDesc;
	
	private List<Object> retList;

	private List<ClassIncome> inCoin;
	
	private List<ClassPayout> outCoin;
	
	private int income;
	
	private int outPay;
	
	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getOutPay() {
		return outPay;
	}

	public void setOutPay(int outPay) {
		this.outPay = outPay;
	}

	private int totalCoin;
	

	public List<ClassIncome> getInCoin() {
		return inCoin;
	}

	public void setInCoin(List<ClassIncome> inCoin) {
		this.inCoin = inCoin;
	}

	public List<ClassPayout> getOutCoin() {
		return outCoin;
	}

	public void setOutCoin(List<ClassPayout> outCoin) {
		this.outCoin = outCoin;
	}

	public int getTotalCoin() {
		return totalCoin;
	}

	public void setTotalCoin(int totalCoin) {
		this.totalCoin = totalCoin;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetDesc() {
		return retDesc;
	}

	public void setRetDesc(String retDesc) {
		this.retDesc = retDesc;
	}

	public List<Object> getRetList() {
		return retList;
	}

	public void setRetList(List<Object> retList) {
		this.retList = retList;
	}
	
	
}
