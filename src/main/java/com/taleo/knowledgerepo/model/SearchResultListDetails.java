package com.taleo.knowledgerepo.model;

import java.util.List;

import org.springframework.stereotype.Component;

public class SearchResultListDetails {

	public List<SearchResult> searchResults;
	public SearchResultInfo searchResultInfo;

	public SearchResultListDetails() {

	}

	public List<SearchResult> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<SearchResult> searchResults) {
		this.searchResults = searchResults;
	}

	public SearchResultInfo getSearchResultInfo() {
		return searchResultInfo;
	}

	public void setSearchResultInfo(SearchResultInfo searchResultInfo) {
		this.searchResultInfo = searchResultInfo;
	}

}
