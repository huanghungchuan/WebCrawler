package com.how2java.service;

import java.util.List;

import com.how2java.pojo.Origithub;

public interface OrigithubService {
	
	boolean insertOrigithub(Origithub  origithub);
	 /*
     * ����������ȡ�������
     */
    public List<Origithub> getGitListByOrigitModel(Origithub origithub);
    
    public List<Origithub> list();
    /*
     * ��������ɾ��
     */
    public void delete(Origithub origithub);
    /*
     * ����idɾ��
     */
    public void deleteById(int id);
    /*
     * ��������
     */
    public int update(Origithub origithub);  
    
    /*
     * ����url����
     */
    public int  updateUrl(Origithub origithub);
}
