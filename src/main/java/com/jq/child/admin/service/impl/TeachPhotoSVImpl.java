package com.jq.child.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jq.child.admin.dao.TeachPhotoMapper;
import com.jq.child.admin.pojo.TeachPhoto;
import com.jq.child.admin.pojo.TeachPhotoExample;
import com.jq.child.admin.pojo.TeachPhotoExample.Criteria;
import com.jq.child.admin.service.ITeachPhotoSV;

@Service("teachPhotoSV")
public class TeachPhotoSVImpl implements ITeachPhotoSV {

	@Resource
	private TeachPhotoMapper photoMapper;
	
	public void insertPhotoInfo(TeachPhoto bean) throws Exception {
		// TODO Auto-generated method stub
		photoMapper.insert(bean);
	}
	
	public void delPhotoById(int photoId) throws Exception {
		// TODO Auto-generated method stub
		
		photoMapper.deleteByPrimaryKey(photoId);
	}

	/**
	 * 查询信息通过班级
	 */
	public List<TeachPhoto> qryTeachPhotoValue(String classNum) throws Exception {
		// TODO Auto-generated method stub
		TeachPhotoExample example = new TeachPhotoExample();
		
		Criteria criteria = example.createCriteria();
		criteria.andExt1EqualTo(classNum);
		List<TeachPhoto> list = photoMapper.selectByExample(example);
		return list;
	}

}
