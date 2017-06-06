package com.taleo.knowledgerepo.service;

import java.util.ArrayList;
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
	
	int countPerPage = 20;

	public List<SearchResult> findAllSearchResults() {
		return dao.findAllSearchResults();
	}

	public SearchResult findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<SearchResult> findAllSearchResultsByKeyword(int pageId, String keyword) {
		List<SearchResult> searchResults = findAllSearchResults();
		List<SearchResult> pagedSearchResults = new ArrayList<>(1);
		int count = searchResults.size();
		int initialCount = countPerPage * (pageId - 1);
		if(searchResults.isEmpty() || (initialCount > count) || initialCount < 0)
		{
			return pagedSearchResults;
		}
		int finalCount = (count > pageId * countPerPage) ? pageId * countPerPage : count;
		
		return searchResults.subList(initialCount, finalCount);
	}

	@Override
	public void save(SearchResult searchResult) {
		dao.save(searchResult);
	}

}
