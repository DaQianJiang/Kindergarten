package com.jq.child.admin.dao;

import com.jq.child.admin.pojo.TeachPhoto;
import com.jq.child.admin.pojo.TeachPhotoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeachPhotoMapper {
    int countByExample(TeachPhotoExample example);

    int deleteByExample(TeachPhotoExample example);

    int deleteByPrimaryKey(Integer photoId);

    int insert(TeachPhoto record);

    int insertSelective(TeachPhoto record);

    List<TeachPhoto> selectByExample(TeachPhotoExample example);

    TeachPhoto selectByPrimaryKey(Integer photoId);

    int updateByExampleSelective(@Param("record") TeachPhoto record, @Param("example") TeachPhotoExample example);

    int updateByExample(@Param("record") TeachPhoto record, @Param("example") TeachPhotoExample example);

    int updateByPrimaryKeySelective(TeachPhoto record);

    int updateByPrimaryKey(TeachPhoto record);
}