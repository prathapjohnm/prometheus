package com.taleo.knowledgerepo.service;

import java.util.List;

import com.taleo.knowledgerepo.model.SearchResult;

public interface SearchResultService {

	SearchResult findById(int id);

	List<SearchResult> findAllSearchResults();

}
