package com.how2java.service;

import java.util.List;

import com.how2java.pojo.MvnUrlModel;

public interface MvnRepService {
	
	public void add(MvnUrlModel mvnUrlModel);
	
	public void delete(MvnUrlModel mvnUrlModel);
	
	public MvnUrlModel get(MvnUrlModel mvnUrlModel);
	
	public List<MvnUrlModel> getList(MvnUrlModel mvnUrlModel);
	
	public void update(MvnUrlModel mvnUrlModel);
	
	public int getNum(MvnUrlModel mvnUrlModel);
	
}
