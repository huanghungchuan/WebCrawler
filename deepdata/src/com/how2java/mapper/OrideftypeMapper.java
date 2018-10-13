package com.how2java.mapper;

import java.util.List;

import com.how2java.pojo.Orideftype;

public interface OrideftypeMapper {
public int add(Orideftype orideftype);  
    
    
    public void delete(int id);  
       
      
    public Orideftype get(int id);  
     
      
    public int update(Orideftype orideftype);   
       
      
    public List<Orideftype> list();
    
    public void init_insert(Orideftype orideftype);
    
    
    public List<Orideftype> defTypeList();
    
    
    public List<Orideftype> getDouData(Orideftype orideftype);
    
}
