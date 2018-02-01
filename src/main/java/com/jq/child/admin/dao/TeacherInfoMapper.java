package com.jq.child.admin.dao;

import com.jq.child.admin.pojo.StudentInfo;
import com.jq.child.admin.pojo.TeacherInfo;
import com.jq.child.admin.pojo.TeacherInfoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TeacherInfoMapper {
    int countByExample(TeacherInfoExample example);

    int deleteByExample(TeacherInfoExample example);

    int insert(TeacherInfo record);

    int insertSelective(TeacherInfo record);

    List<TeacherInfo> selectByExample(TeacherInfoExample example);
    //单个模糊查询
    List<TeacherInfo> selectOneTeacherInfo(Map paramMap);

    int updateByExampleSelective(@Param("record") TeacherInfo record, @Param("example") TeacherInfoExample example);

    int updateByExample(@Param("record") TeacherInfo record, @Param("example") TeacherInfoExample example);
}