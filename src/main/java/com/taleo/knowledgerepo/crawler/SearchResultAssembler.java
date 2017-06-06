package com.taleo.knowledgerepo.crawler;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.taleo.knowledgerepo.dao.SearchResultDaoImpl;
import com.taleo.knowledgerepo.model.SearchResult;

@Component
@Transactional
public class SearchResultAssembler {
	private SearchResult srcResult;
	
	@Autowired
	private SearchResultDaoImpl srcDaoImpl;

	public SearchResult assembleAndSave(Document document, String uRL) {
		assemble (document,uRL);
		saveSearchResult();
		return this.srcResult;
	}


	private void assemble(Document document, String uRL) {
		srcResult = new SearchResult();
		srcResult.setUrl(uRL);
		srcResult.setTitle(document.title());
		srcResult.setDescription("Description of document");
	}

	private void saveSearchResult() {
		srcDaoImpl.save(srcResult);
	}

}
