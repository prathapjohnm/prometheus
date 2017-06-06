package com.taleo.knowledgerepo.model;

import org.springframework.stereotype.Component;

@Component
public class SearchResultInfo {

	public int totalCount;
	public int pageId;

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
	
}
