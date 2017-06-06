package com.taleo.knowledgerepo.dao;

import java.util.List;

import com.taleo.knowledgerepo.model.SearchResult;

public interface SearchResultDao {

	SearchResult findById(int id);
		
	List<SearchResult> findAllSearchResults();

}

