package com.taleo.knowledgerepo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taleo.knowledgerepo.model.SearchResult;
import com.taleo.knowledgerepo.searcher.SolrSearchService;

@Repository("searchResultDao")
public class SearchResultDaoImpl extends AbstractDao<Integer, SearchResult> implements SearchResultDao {

	@Autowired
	SolrSearchService solrSearchService;

	public SearchResult findById(int id) {
		SearchResult searchResult = getByKey(id);

		return searchResult;
	}

	@Override
	public List<SearchResult> findAllSearchResults(String keyword) {
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		List<Integer> docIds = solrSearchService.doQuery(keyword);
		for (Integer docId : docIds) {
			searchResults.add(findById(docId));
		}

		return searchResults;
	}

	@Override
	public void save(SearchResult searchResult) {
		persist(searchResult);
	}

}
