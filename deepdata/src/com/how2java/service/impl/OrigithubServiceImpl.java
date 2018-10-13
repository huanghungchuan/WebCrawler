package com.how2java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.mapper.OrigithubMapper;
import com.how2java.pojo.Origithub;
import com.how2java.service.OrigithubService;

@Service
public class OrigithubServiceImpl implements OrigithubService {
	@Autowired
	OrigithubMapper origithubMapper;
	@Override
	public boolean insertOrigithub(Origithub origithub) {
		// TODO Auto-generated method stub
		int re = origithubMapper.add(origithub);
		if(re > 0) {
			return true;
		}
		return false;
	}
	 /*
     * ����������ȡ�������
     */
    @Override
	public List<Origithub> getGitListByOrigitModel(Origithub origithub) {
		// TODO Auto-generated method stub
    	List<Origithub> origitList = origithubMapper.getGitListByOrigitModel(origithub);
		return origitList;
	}
    /*
     * ��������ɾ��
     * @see com.how2java.service.OrigithubService#delete(com.how2java.pojo.Origithub)
     */
	@Override
	public void delete(Origithub origithub) {
		// TODO Auto-generated method stub
		origithubMapper.delete(origithub);
	}
	/*
     * ����idɾ��
     */
	@Override
	public void deleteById(int id) {
		Origithub origithub = new Origithub();
		origithub.setId(id);
		origithubMapper.delete(origithub);
	}
	/*
     * ��������
     */
	@Override
	public int update(Origithub origithub) {
		// TODO Auto-generated method stub
		return origithubMapper.update(origithub);
	}
	 /*
     * ����url����
     */
	@Override
	public int updateUrl(Origithub origithub) {
		// TODO Auto-generated method stub
		return origithubMapper.updateUrl(origithub);
	}
	/* (non-Javadoc)
	 * @see com.how2java.service.OrigithubService#list()
	 */
	@Override
	public List<Origithub> list() {
		// TODO Auto-generated method stub
		return origithubMapper.list();
	}

}
