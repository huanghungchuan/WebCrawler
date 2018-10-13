/**
 * 
 */
package com.how2java.service;

import java.util.List;

import com.how2java.pojo.SearchCodeModel;

/**
 * @author changjq
 *
 */
public interface SearchCodeService {
public void add(SearchCodeModel searchCodeModel);
	
	public void delete(SearchCodeModel searchCodeModel);
	
	public List<SearchCodeModel> get(SearchCodeModel searchCodeModel);
	
	public void update(SearchCodeModel searchCodeModel);
	public List<SearchCodeModel> get();
	
}
