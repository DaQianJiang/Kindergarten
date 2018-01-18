package com.jq.child.student.dao;

import com.jq.child.student.pojo.ClassIncome;
import com.jq.child.student.pojo.ClassIncomeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassIncomeMapper {
    int countByExample(ClassIncomeExample example);

    int deleteByExample(ClassIncomeExample example);

    int deleteByPrimaryKey(Integer incomeId);

    int insert(ClassIncome record);

    int insertSelective(ClassIncome record);

    List<ClassIncome> selectByExample(ClassIncomeExample example);

    ClassIncome selectByPrimaryKey(Integer incomeId);

    int updateByExampleSelective(@Param("record") ClassIncome record, @Param("example") ClassIncomeExample example);

    int updateByExample(@Param("record") ClassIncome record, @Param("example") ClassIncomeExample example);

    int updateByPrimaryKeySelective(ClassIncome record);

    int updateByPrimaryKey(ClassIncome record);
}