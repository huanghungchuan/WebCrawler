package com.how2java.mapper;

import java.util.List;

import com.how2java.pojo.SearchCodeModel;


public interface SearchCodeMapper {
	public void add(SearchCodeModel searchCodeModel);
	
	public void delete(SearchCodeModel searchCodeModel);
	
	public List<SearchCodeModel> get();
	
	public void update(SearchCodeModel searchCodeModel);
}
