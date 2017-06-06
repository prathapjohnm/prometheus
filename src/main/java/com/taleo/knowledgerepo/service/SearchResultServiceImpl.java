package com.taleo.knowledgerepo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taleo.knowledgerepo.dao.SearchResultDao;
import com.taleo.knowledgerepo.model.SearchResult;

@Service("searchResultService")
@Transactional
public class SearchResultServiceImpl implements SearchResultService {

	@Autowired
	private SearchResultDao dao;

	public List<SearchResult> findAllSearchResults() {
		return dao.findAllSearchResults();
	}

	public SearchResult findById(int id) {
		return dao.findById(id);
	}

}
