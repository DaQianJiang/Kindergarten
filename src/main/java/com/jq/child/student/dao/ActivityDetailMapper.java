package com.jq.child.student.dao;

import com.jq.child.student.pojo.ActivityDetail;
import com.jq.child.student.pojo.ActivityDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityDetailMapper {
    int countByExample(ActivityDetailExample example);

    int deleteByExample(ActivityDetailExample example);

    int insert(ActivityDetail record);

    int insertSelective(ActivityDetail record);

    List<ActivityDetail> selectByExample(ActivityDetailExample example);

    int updateByExampleSelective(@Param("record") ActivityDetail record, @Param("example") ActivityDetailExample example);

    int updateByExample(@Param("record") ActivityDetail record, @Param("example") ActivityDetailExample example);
}