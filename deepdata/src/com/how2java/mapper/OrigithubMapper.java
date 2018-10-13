package com.how2java.mapper;

import java.util.List;

import com.how2java.pojo.Origithub;



public interface OrigithubMapper {
	public int add(Origithub origithub);  
    
    /*
     * 根据条件删除
     */
    public void delete(Origithub origithub);  
       
      
    public Origithub get(int id);  
     
    /*
     * 更新数据
     */
    public int update(Origithub origithub);   
       
      
    public List<Origithub> list();
    /*
     * 根据条件获取相关数据
     */
    public List<Origithub> getGitListByOrigitModel(Origithub origithub);
    /*
     * 更新url链接
     */
    public int  updateUrl(Origithub origithub);
}
