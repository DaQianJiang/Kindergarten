package com.jq.child.admin.service;

import java.util.List;

import com.jq.child.admin.pojo.ParentAuthority;

public interface IParentAuthoritySV {
	public void insertAuthor(ParentAuthority bean)throws Exception;
	
	public List<ParentAuthority> qryAuthorBySno(String sno)throws Exception;
	
	public void delAuthorBySno(String sno)throws Exception;
	
	public List<ParentAuthority> qryAuthorByClass(String classNum)throws Exception;
}
