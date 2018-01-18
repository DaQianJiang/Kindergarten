package com.jq.child.admin.dao;

import com.jq.child.admin.pojo.ActivityConfig;
import com.jq.child.admin.pojo.ActivityConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityConfigMapper {
    int countByExample(ActivityConfigExample example);

    int deleteByExample(ActivityConfigExample example);

    int deleteByPrimaryKey(Integer activityId);

    int insert(ActivityConfig record);

    int insertSelective(ActivityConfig record);

    List<ActivityConfig> selectByExample(ActivityConfigExample example);

    ActivityConfig selectByPrimaryKey(Integer activityId);

    int updateByExampleSelective(@Param("record") ActivityConfig record, @Param("example") ActivityConfigExample example);

    int updateByExample(@Param("record") ActivityConfig record, @Param("example") ActivityConfigExample example);

    int updateByPrimaryKeySelective(ActivityConfig record);

    int updateByPrimaryKey(ActivityConfig record);
}