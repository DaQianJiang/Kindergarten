package com.jq.child.admin.service;

import java.util.List;

import com.jq.child.admin.pojo.StudentInfo;
import com.jq.child.admin.pojo.TeacherInfo;
import com.jq.child.util.OutPutParamUtil;

/**
 * 
 * @author jq
 *
 */
public interface ITeacherInfoSV {
	
	public List<TeacherInfo> qryTeacherInfoByNum(String teacherNum)throws Exception;

	public boolean saveTeacherInfo(TeacherInfo teacherinfo)throws Exception;

	public List<TeacherInfo> getTeacherInfoBySno(String teacherNum) throws Exception;

	public List<TeacherInfo> qryOneTeacherInfo(String t_name, String t_number, String t_class);
	public OutPutParamUtil delTeacherInfo(List<String> list);
	
	
}
