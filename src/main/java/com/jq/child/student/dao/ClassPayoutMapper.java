package com.jq.child.student.dao;

import com.jq.child.student.pojo.ClassPayout;
import com.jq.child.student.pojo.ClassPayoutExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassPayoutMapper {
    int countByExample(ClassPayoutExample example);

    int deleteByExample(ClassPayoutExample example);

    int deleteByPrimaryKey(Integer payoutId);

    int insert(ClassPayout record);

    int insertSelective(ClassPayout record);

    List<ClassPayout> selectByExample(ClassPayoutExample example);

    ClassPayout selectByPrimaryKey(Integer payoutId);

    int updateByExampleSelective(@Param("record") ClassPayout record, @Param("example") ClassPayoutExample example);

    int updateByExample(@Param("record") ClassPayout record, @Param("example") ClassPayoutExample example);

    int updateByPrimaryKeySelective(ClassPayout record);

    int updateByPrimaryKey(ClassPayout record);
}