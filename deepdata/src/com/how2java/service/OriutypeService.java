package com.how2java.service;

import java.util.List;

import com.how2java.pojo.Oriutype;

public interface OriutypeService {
	
	boolean insertOriutype(Oriutype  oriutype);
	
	public void updateSfsc();
	/*
	 * 插入数据
	 */
	public int  insertByOriutype(Oriutype oriutype);
	/*
	 * 删除数据
	 */
	public void   deleteUtype(Oriutype oriutype);
	/*
	 * 根据条件查询
	 */
	public List<Oriutype> getUtypeList(Oriutype oriutype);
	/*
	 * 更新数据
	 */
	public void updateOriutype(Oriutype oriutype);
	
	
	public void updateSfscById(Oriutype oriutype);
	
	/*
	 * 删除7天前的数据
	 */
	public void deleteUtypeSevenData(Oriutype oriutype);
}
