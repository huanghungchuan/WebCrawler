package com.how2java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.mapper.MvnMapper;
import com.how2java.pojo.MvnUrlModel;
import com.how2java.service.MvnRepService;
@Service
public class MvnRepServiceImpl implements MvnRepService {
	@Autowired
	MvnMapper mvnMapper;	
	@Override
	public void add(MvnUrlModel mvnUrlModel) {
		// TODO Auto-generated method stub
		mvnMapper.add(mvnUrlModel);
	}

	@Override
	public void delete(MvnUrlModel mvnUrlModel) {
		// TODO Auto-generated method stub
		mvnMapper.delete(mvnUrlModel);
	}

	@Override
	public MvnUrlModel get(MvnUrlModel mvnUrlModel) {
		// TODO Auto-generated method stub
		return mvnMapper.get(mvnUrlModel);
	}

	@Override
	public List<MvnUrlModel> getList(MvnUrlModel mvnUrlModel) {
		// TODO Auto-generated method stub
		return mvnMapper.getList(mvnUrlModel);
	}

	@Override
	public void update(MvnUrlModel mvnUrlModel) {
		// TODO Auto-generated method stub
		mvnMapper.update(mvnUrlModel);
	}

	@Override
	public int getNum(MvnUrlModel mvnUrlModel) {
		// TODO Auto-generated method stub
		return mvnMapper.getNum(mvnUrlModel).getNum();
	}

}
