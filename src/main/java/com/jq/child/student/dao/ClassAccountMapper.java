package com.jq.child.student.dao;

import com.jq.child.student.pojo.ClassAccount;
import com.jq.child.student.pojo.ClassAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassAccountMapper {
    int countByExample(ClassAccountExample example);

    int deleteByExample(ClassAccountExample example);

    int deleteByPrimaryKey(Integer accountId);

    int insert(ClassAccount record);

    int insertSelective(ClassAccount record);

    List<ClassAccount> selectByExample(ClassAccountExample example);

    ClassAccount selectByPrimaryKey(Integer accountId);

    int updateByExampleSelective(@Param("record") ClassAccount record, @Param("example") ClassAccountExample example);

    int updateByExample(@Param("record") ClassAccount record, @Param("example") ClassAccountExample example);

    int updateByPrimaryKeySelective(ClassAccount record);

    int updateByPrimaryKey(ClassAccount record);
}