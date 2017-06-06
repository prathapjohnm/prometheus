package com.taleo.knowledgerepo.service;

import java.util.List;

import com.taleo.knowledgerepo.model.SearchResult;
import com.taleo.knowledgerepo.model.SearchResultListDetails;

public interface SearchResultService {

	SearchResult findById(int id);

	List<SearchResult> findAllSearchResults(String keyword);

	SearchResultListDetails findAllSearchResultsByKeyword(int pageId, String keyword);
	
	void save(SearchResult searchResult);

}
