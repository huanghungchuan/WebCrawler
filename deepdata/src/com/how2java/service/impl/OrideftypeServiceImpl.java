package com.how2java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.mapper.OrideftypeMapper;
import com.how2java.pojo.Orideftype;
import com.how2java.service.OrideftypeService;
@Service
public class OrideftypeServiceImpl implements OrideftypeService{
	@Autowired
	OrideftypeMapper orideftypeMapper;
	@Override
	public void init_insert(Orideftype orideftype) {
		// TODO Auto-generated method stub
		orideftypeMapper.init_insert(orideftype);
	}
	@Override
	public List<Orideftype> defTypeList() {
		// TODO Auto-generated method stub
		 List<Orideftype>  list = orideftypeMapper.defTypeList();
		return list;
	}
	@Override
	public List<Orideftype> list() {
		// TODO Auto-generated method stub
		List<Orideftype>  list = orideftypeMapper.list();
		return list;
	}
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		orideftypeMapper.delete(id);  
	}
	@Override
	public List<Orideftype> getDouData(Orideftype orideftype) {
		// TODO Auto-generated method stub
		List<Orideftype> list = orideftypeMapper.getDouData(orideftype);
		return list;
	}

}
