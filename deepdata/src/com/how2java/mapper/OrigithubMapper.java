package com.how2java.mapper;

import java.util.List;

import com.how2java.pojo.Origithub;



public interface OrigithubMapper {
	public int add(Origithub origithub);  
    
    /*
     * ��������ɾ��
     */
    public void delete(Origithub origithub);  
       
      
    public Origithub get(int id);  
     
    /*
     * ��������
     */
    public int update(Origithub origithub);   
       
      
    public List<Origithub> list();
    /*
     * ����������ȡ�������
     */
    public List<Origithub> getGitListByOrigitModel(Origithub origithub);
    /*
     * ����url����
     */
    public int  updateUrl(Origithub origithub);
}
