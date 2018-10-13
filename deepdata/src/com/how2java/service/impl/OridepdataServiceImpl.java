package com.how2java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.how2java.mapper.OridepdataMapper;
import com.how2java.pojo.Oridepdata;
import com.how2java.service.OridepdataService;
@Service
public class OridepdataServiceImpl implements OridepdataService{
	@Autowired
	OridepdataMapper oridepdataMapper;
	@Override
	public boolean insertOridepdata(Oridepdata oridepdata) {
		// TODO Auto-generated method stub
		int re = oridepdataMapper.add(oridepdata);
		if(re > 0) {
			return true;
		}		
		return false;
	}

	public List<Oridepdata> list(Oridepdata oridepdata ){
		List<Oridepdata> list = oridepdataMapper.list(oridepdata);
		return list;
		
	}

	@Override
	public void updateDlevel() {
		// TODO Auto-generated method stub
		Oridepdata oridepdata  = new Oridepdata();
		oridepdataMapper.updateDlevel(oridepdata);
	}

	@Override
	public void update(Oridepdata oridepdata) {
		// TODO Auto-generated method stub
		oridepdataMapper.update(oridepdata);
	}

	@Override
	public void updateMdvalue(Oridepdata oridepdata) {
		// TODO Auto-generated method stub
		oridepdataMapper.updateMdvalue(oridepdata);
	}

	@Override
	public List<Oridepdata> getListByQuery(Oridepdata oridepdata) {
		// TODO Auto-generated method stub
		return oridepdataMapper.getListByQuery(oridepdata);
	}
	
	@Override
	public boolean getMdListByMdvalue(String mdvalue) {
		// TODO Auto-generated method stub
		Oridepdata oridepdata = new Oridepdata();
		oridepdata.setMdvalue(mdvalue);
	    List<Oridepdata> list =  oridepdataMapper.getMdListByMdvalue(oridepdata);
		if(list!= null && list.size() > 0) 	return true;	
		return false;
	}

	@Override
	public void deleteLInfoSeven(Oridepdata oridepdata) {
		// TODO Auto-generated method stub
		oridepdataMapper.deleteLInfoSeven(oridepdata);
	}

}
