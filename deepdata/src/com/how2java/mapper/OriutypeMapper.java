package com.how2java.mapper;

import java.util.List;

import com.how2java.pojo.Oriutype;

public interface OriutypeMapper {
	public int add(Oriutype oriutype );
	
	public void updateSfsc(Oriutype oriutype);
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
