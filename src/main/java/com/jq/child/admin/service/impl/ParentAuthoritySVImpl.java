package com.jq.child.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jq.child.admin.dao.ParentAuthorityMapper;
import com.jq.child.admin.pojo.ParentAuthority;
import com.jq.child.admin.pojo.ParentAuthorityExample;
import com.jq.child.admin.pojo.ParentAuthorityExample.Criteria;
import com.jq.child.admin.service.IParentAuthoritySV;

@Service("authoritySV")
public class ParentAuthoritySVImpl implements IParentAuthoritySV{

	@Resource
	private ParentAuthorityMapper authorityMapper;
	public void insertAuthor(ParentAuthority bean) throws Exception {
		// TODO Auto-generated method stub
		authorityMapper.insert(bean);
	}
	public List<ParentAuthority> qryAuthorBySno(String sno) throws Exception {
		// TODO Auto-generated method stub
		ParentAuthorityExample example = new ParentAuthorityExample();
		if(sno!=null){
			Criteria criteria = example.createCriteria();
			criteria.andSnoEqualTo(sno);
		}
		List<ParentAuthority> list = authorityMapper.selectByExample(example);
		return list;
	}
	public void delAuthorBySno(String sno) throws Exception {
		// TODO Auto-generated method stub
		ParentAuthorityExample example = new ParentAuthorityExample();
	
		Criteria criteria = example.createCriteria();
		criteria.andSnoEqualTo(sno);
		
		authorityMapper.deleteByExample(example);
	}
	/**
	 * ∞¥∞‡º∂≤È—Ø
	 */
	public List<ParentAuthority> qryAuthorByClass(String classNum) throws Exception {
		// TODO Auto-generated method stub
		ParentAuthorityExample example = new ParentAuthorityExample();
		Criteria criteria = example.createCriteria();
		criteria.andExt1EqualTo(classNum);
		List<ParentAuthority> list = authorityMapper.selectByExample(example);
		return list;
	}

}
