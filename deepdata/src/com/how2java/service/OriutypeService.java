package com.how2java.service;

import java.util.List;

import com.how2java.pojo.Oriutype;

public interface OriutypeService {
	
	boolean insertOriutype(Oriutype  oriutype);
	
	public void updateSfsc();
	/*
	 * ��������
	 */
	public int  insertByOriutype(Oriutype oriutype);
	/*
	 * ɾ������
	 */
	public void   deleteUtype(Oriutype oriutype);
	/*
	 * ����������ѯ
	 */
	public List<Oriutype> getUtypeList(Oriutype oriutype);
	/*
	 * ��������
	 */
	public void updateOriutype(Oriutype oriutype);
	
	
	public void updateSfscById(Oriutype oriutype);
	
	/*
	 * ɾ��7��ǰ������
	 */
	public void deleteUtypeSevenData(Oriutype oriutype);
}
