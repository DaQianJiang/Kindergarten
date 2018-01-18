package com.jq.child.admin.dao;

import com.jq.child.admin.pojo.ChildRemarks;
import com.jq.child.admin.pojo.ChildRemarksExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ChildRemarksMapper {
    int countByExample(ChildRemarksExample example);

    int deleteByExample(ChildRemarksExample example);

    int insert(ChildRemarks record);

    int insertSelective(ChildRemarks record);

    List<ChildRemarks> selectByExample(ChildRemarksExample example);

    int updateByExampleSelective(@Param("record") ChildRemarks record, @Param("example") ChildRemarksExample example);

    int updateByExample(@Param("record") ChildRemarks record, @Param("example") ChildRemarksExample example);
//幼儿点评信息模糊查询
	List<ChildRemarks> selectChildRmark(Map paramMap);
}