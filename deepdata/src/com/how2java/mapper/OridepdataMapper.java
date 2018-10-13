package com.how2java.mapper;

import java.util.List;

import com.how2java.pojo.Oridepdata;


public interface OridepdataMapper {
	
	public int add(Oridepdata oridepdata );
	
	public List<Oridepdata> list(Oridepdata oridepdata );
	
	public void updateDlevel(Oridepdata oridepdata);
	
	public void update(Oridepdata oridepdata);
	
	public void updateMdvalue(Oridepdata oridepdata);
	
	public List<Oridepdata> getListByQuery(Oridepdata oridepdata);
	
	
	public List<Oridepdata> getMdListByMdvalue(Oridepdata oridepdata);
	
	
	public void deleteLInfoSeven(Oridepdata oridepdata );
}
