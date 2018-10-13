package com.how2java.mapper;

import java.util.List;

import com.how2java.pojo.MvnUrlModel;

public interface MvnMapper {
	public void add(MvnUrlModel mvnUrlModel);
	
	public void delete(MvnUrlModel mvnUrlModel);
	
	public MvnUrlModel get(MvnUrlModel mvnUrlModel);
	
	public List<MvnUrlModel> getList(MvnUrlModel mvnUrlModel);
	
	public void update(MvnUrlModel mvnUrlModel);
	
	public MvnUrlModel getNum(MvnUrlModel mvnUrlModel);
	
}
