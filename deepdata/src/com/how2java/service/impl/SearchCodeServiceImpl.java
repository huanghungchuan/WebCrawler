/**
 * 
 */
package com.how2java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.mapper.SearchCodeMapper;
import com.how2java.pojo.SearchCodeModel;
import com.how2java.service.SearchCodeService;

/**
 * @author changjq
 *
 */
@Service
public class SearchCodeServiceImpl implements SearchCodeService {
	@Autowired
	SearchCodeMapper searchCodeMapper;
	/* (non-Javadoc)
	 * @see com.how2java.service.SearchCodeService#add(com.how2java.pojo.SearchCodeModel)
	 */
	@Override
	public void add(SearchCodeModel searchCodeModel) {
		// TODO Auto-generated method stub
		searchCodeMapper.add(searchCodeModel);
	}

	/* (non-Javadoc)
	 * @see com.how2java.service.SearchCodeService#delete(com.how2java.pojo.SearchCodeModel)
	 */
	@Override
	public void delete(SearchCodeModel searchCodeModel) {
		// TODO Auto-generated method stub
		searchCodeMapper.delete(searchCodeModel);
	}

	/* (non-Javadoc)
	 * @see com.how2java.service.SearchCodeService#get(com.how2java.pojo.SearchCodeModel)
	 */
	@Override
	public List<SearchCodeModel> get(SearchCodeModel searchCodeModel) {
		// TODO Auto-generated method stub
		return searchCodeMapper.get();
	}

	/* (non-Javadoc)
	 * @see com.how2java.service.SearchCodeService#update(com.how2java.pojo.SearchCodeModel)
	 */
	@Override
	public void update(SearchCodeModel searchCodeModel) {
		// TODO Auto-generated method stub
		searchCodeMapper.update(searchCodeModel);
	}

	/* (non-Javadoc)
	 * @see com.how2java.service.SearchCodeService#get()
	 */
	@Override
	public List<SearchCodeModel> get() {
		// TODO Auto-generated method stub
		return searchCodeMapper.get();
	}

}
