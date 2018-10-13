package com.how2java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.mapper.SivaMapper;
import com.how2java.pojo.SivaModel;
import com.how2java.service.SivaService;
@Service
public class SivaServiceImpl implements SivaService{
	@Autowired
	SivaMapper sivaMapper;
	@Override
	public List<SivaModel> get() {
		// TODO Auto-generated method stub
		return   sivaMapper.get();
	}

	@Override
	public void update(SivaModel sivaModel) {
		// TODO Auto-generated method stub
		sivaMapper.update(sivaModel);
	}
	@Override
	public SivaModel getSiva(SivaModel sivaModel) {
		return sivaMapper.getSiva(sivaModel);
	}

	@Override
	public List<SivaModel> getList() {
		// TODO Auto-generated method stub
		return  sivaMapper.getList() ;
	}

	@Override
	public void insertSivaFile(SivaModel sivaModel) {
		// TODO Auto-generated method stub
		sivaMapper.insertSivaFile(sivaModel);
	}
	
	
}
