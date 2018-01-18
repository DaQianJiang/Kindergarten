package com.jq.child.admin.dao;

import com.jq.child.admin.pojo.ParentDuty;
import com.jq.child.admin.pojo.ParentDutyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParentDutyMapper {
    int countByExample(ParentDutyExample example);

    int deleteByExample(ParentDutyExample example);

    int insert(ParentDuty record);

    int insertSelective(ParentDuty record);

    List<ParentDuty> selectByExample(ParentDutyExample example);

    int updateByExampleSelective(@Param("record") ParentDuty record, @Param("example") ParentDutyExample example);

    int updateByExample(@Param("record") ParentDuty record, @Param("example") ParentDutyExample example);
}