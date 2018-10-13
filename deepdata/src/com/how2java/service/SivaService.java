package com.how2java.service;

import java.util.List;

import com.how2java.pojo.SivaModel;

public interface SivaService {
	
	public List<SivaModel> get();
	
	public void update(SivaModel sivaModel);
	
	public SivaModel getSiva(SivaModel sivaModel) ;
	
	public List<SivaModel> getList();
	
	public void insertSivaFile(SivaModel sivaModel);
}
