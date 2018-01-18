package com.jq.child.admin.service;

import java.util.List;

import com.jq.child.admin.pojo.StudentInfo;
import com.jq.child.util.OutPutParamUtil;

public interface IStudentInfoSV {
	public boolean saveStuInfo(StudentInfo value)throws Exception;
	
	public List<StudentInfo> getStuInfoBySno(String sno)throws Exception;
	
	public List<StudentInfo> qryOneStuInfo(String name, String sno, String className)throws Exception;
	
	public OutPutParamUtil delStudentInfo(List<String> list)throws Exception;

	public List<StudentInfo> qryStuInfo(String classNum, String sname);

	public void updateFeatureFlage() throws Exception;
}
