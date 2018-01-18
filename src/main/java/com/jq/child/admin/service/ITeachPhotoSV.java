package com.jq.child.admin.service;

import java.util.List;

import com.jq.child.admin.pojo.TeachPhoto;

public interface ITeachPhotoSV {
	public void insertPhotoInfo(TeachPhoto bean)throws Exception;
	
	public void delPhotoById(int photoId)throws Exception;
	
	public List<TeachPhoto> qryTeachPhotoValue(String classNum)throws Exception;
}
