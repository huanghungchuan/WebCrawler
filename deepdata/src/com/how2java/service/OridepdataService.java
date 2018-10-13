package com.how2java.service;

import java.util.List;

import com.how2java.pojo.Oridepdata;

public interface OridepdataService {
	public boolean insertOridepdata(Oridepdata  oridepdata);
	
	public List<Oridepdata> list(Oridepdata oridepdata );
	
	public void updateDlevel();
	
	public void update(Oridepdata oridepdata);
	
	public void updateMdvalue(Oridepdata oridepdata);
	
	public List<Oridepdata> getListByQuery(Oridepdata oridepdata);
	
	
	public boolean getMdListByMdvalue(String mdvalue);
	
	public void deleteLInfoSeven(Oridepdata oridepdata );
}
