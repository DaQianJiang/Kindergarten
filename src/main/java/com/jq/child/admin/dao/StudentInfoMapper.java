package com.jq.child.admin.dao;

import com.jq.child.admin.pojo.StudentInfo;
import com.jq.child.admin.pojo.StudentInfoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface StudentInfoMapper {
    int countByExample(StudentInfoExample example);

    int deleteByExample(StudentInfoExample example);

    int insert(StudentInfo record);

    int insertSelective(StudentInfo record);

    List<StudentInfo> selectByExample(StudentInfoExample example);
    
    //模糊单个查询
    List<StudentInfo> selectOneStuInfo(Map paramMap);
    
    int updateByExampleSelective(@Param("record") StudentInfo record, @Param("example") StudentInfoExample example);

    int updateByExample(@Param("record") StudentInfo record, @Param("example") StudentInfoExample example);
}