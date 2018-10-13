package com.how2java.service;

import java.util.List;

import com.how2java.pojo.Orideftype;

public interface OrideftypeService {

	public void init_insert(Orideftype orideftype);//初始化数据
	public List<Orideftype> defTypeList();
	
	public List<Orideftype> list();
	
	  public void delete(int id);  
	  
	  public List<Orideftype> getDouData(Orideftype orideftype);
}
