package com.taleo.knowledgerepo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taleo.knowledgerepo.dao.SearchResultDao;
import com.taleo.knowledgerepo.model.SearchResult;
import com.taleo.knowledgerepo.model.SearchResultInfo;
import com.taleo.knowledgerepo.model.SearchResultListDetails;

@Service("searchResultService")
@Transactional
public class SearchResultServiceImpl implements SearchResultService {

	@Autowired
	private SearchResultDao dao;
	
	int countPerPage = 20;

	public List<SearchResult> findAllSearchResults(String keyword) {
		return dao.findAllSearchResults(keyword);
	}

	public SearchResult findById(int id) {
		return dao.findById(id);
	}

	@Override
	public SearchResultListDetails findAllSearchResultsByKeyword(int pageId, String keyword) {
		List<SearchResult> searchResults = findAllSearchResults(keyword);
		List<SearchResult> pagedSearchResults = new ArrayList<>(1);
		
		int count = searchResults.size();
		int initialCount = countPerPage * (pageId - 1);
		
		if(searchResults.isEmpty() || (initialCount > count) || initialCount < 0)
		{
			return prepareResponse(pagedSearchResults, pageId, count);
		}
		int finalCount = (count > pageId * countPerPage) ? pageId * countPerPage : count;
		
		return prepareResponse(searchResults.subList(initialCount, finalCount), pageId, count);
	}

	@Override
	public void save(SearchResult searchResult) {
		dao.save(searchResult);
	}
	
	public SearchResultListDetails prepareResponse(List<SearchResult> searchResults, int pageId, int totalCount)
	{
		SearchResultInfo resultInfo = new SearchResultInfo();
		resultInfo.setTotalCount(totalCount);
		resultInfo.setPageId(pageId);
		
		SearchResultListDetails searchResultDetails = new SearchResultListDetails();
		searchResultDetails.setSearchResults(searchResults);
		searchResultDetails.setSearchResultInfo(resultInfo);
		
		return searchResultDetails;
	}

}
