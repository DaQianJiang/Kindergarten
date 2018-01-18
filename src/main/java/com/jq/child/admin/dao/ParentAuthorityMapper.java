package com.jq.child.admin.dao;

import com.jq.child.admin.pojo.ParentAuthority;
import com.jq.child.admin.pojo.ParentAuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParentAuthorityMapper {
    int countByExample(ParentAuthorityExample example);

    int deleteByExample(ParentAuthorityExample example);

    int deleteByPrimaryKey(Integer authorityId);

    int insert(ParentAuthority record);

    int insertSelective(ParentAuthority record);

    List<ParentAuthority> selectByExample(ParentAuthorityExample example);

    ParentAuthority selectByPrimaryKey(Integer authorityId);

    int updateByExampleSelective(@Param("record") ParentAuthority record, @Param("example") ParentAuthorityExample example);

    int updateByExample(@Param("record") ParentAuthority record, @Param("example") ParentAuthorityExample example);

    int updateByPrimaryKeySelective(ParentAuthority record);

    int updateByPrimaryKey(ParentAuthority record);
}