package com.how2java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.mapper.OriutypeMapper;
import com.how2java.pojo.Oriutype;
import com.how2java.service.OriutypeService;
@Service
public class OriutypeServiceImpl implements OriutypeService {
	@Autowired
	OriutypeMapper oriutypeMapper;
	@Override
	public boolean insertOriutype(Oriutype oriutype) {
		// TODO Auto-generated method stub
		int re = oriutypeMapper.add(oriutype);
		if(re > 0) {
			return true;
		}
		return false;
	}
	@Override
	public void updateSfsc() {
		// TODO Auto-generated method stub
		Oriutype oriutype = new Oriutype();
		oriutypeMapper.updateSfsc(oriutype);
	}
	/*
	 * 插入数据
	 */
	@Override
	public int insertByOriutype(Oriutype oriutype) {
		// TODO Auto-generated method stub
		return oriutypeMapper.insertByOriutype(oriutype);
	}
	/*
	 * 删除数据
	 */
	@Override
	public void deleteUtype(Oriutype oriutype) {
		// TODO Auto-generated method stub
		oriutypeMapper.deleteUtype(oriutype);
	}
	/*
	 * 根据条件查询
	 */
	@Override
	public List<Oriutype> getUtypeList(Oriutype oriutype) {
		// TODO Auto-generated method stub
		return oriutypeMapper.getUtypeList(oriutype);
	}
	/*
	 * 更新数据
	 */
	@Override
	public void updateOriutype(Oriutype oriutype) {
		// TODO Auto-generated method stub
		oriutypeMapper.updateOriutype(oriutype);
	}
	@Override
	public void updateSfscById(Oriutype oriutype) {
		// TODO Auto-generated method stub
		oriutypeMapper.updateSfscById(oriutype);
	}
	@Override
	public void deleteUtypeSevenData(Oriutype oriutype) {
		// TODO Auto-generated method stub
		oriutypeMapper.deleteUtypeSevenData(oriutype);
	}

}
