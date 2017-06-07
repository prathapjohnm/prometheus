package com.taleo.knowledgerepo.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taleo.knowledgerepo.indexer.DocumentIndexer;
import com.taleo.knowledgerepo.model.SearchResult;
import com.taleo.knowledgerepo.util.DocumentTransformationUtil;

import java.io.IOException;

@Component
public class KnowledgeRepoCrawler {

	@Autowired
	URLVisitInfo urlInfo;
	
	@Autowired
	SearchResultAssembler searchResultAssembler;
	
	@Autowired
	DocumentIndexer docIndexer;

	public KnowledgeRepoCrawler() {

	}

	/*
	 * 
	 */
	public void visitPageLinks(String URL, int depth) {
		try {
			urlInfo.prepareURLsToAvoid();
			if (urlInfo.isRequiredToVisit(URL, depth)) {
				System.out.println(">> shouldVisit : true");

    			Document document = Jsoup.connect(URL).get();
    			//String testString = document.select("body").text();
    			Elements linksOnPage = getLinksOnPage(document);
    			//TODO
    			/* send the document to DAO and Service to sync data to lookup table */
    			Integer iDocId = saveSearchResultEntity(document,URL);
    			//TODO
    			/* send the document to indexer for data scraping and indexation */
    			sendDocForIndexation(document,URL,iDocId);
    			
    			depth++;
    			for (Element page : linksOnPage) {
    				visitPageLinks(page.attr("abs:href"), depth);
    			}
    		}
    	} catch (IOException e) {
    		System.err.println("For '" + URL + "': " + e.getMessage());
    	}
    }

	private void sendDocForIndexation(Document document, String uRL, Integer iDocId) {
		docIndexer.indexDocument(uRL, iDocId,"Page", DocumentTransformationUtil.getKeywordsForIndexer(document), document.title());
	}
	private Integer saveSearchResultEntity(Document document, String uRL) {
		SearchResult srcResult = searchResultAssembler.assembleAndSave(document, uRL);
		return srcResult.getId();
	}

	private Elements getLinksOnPage(Document document) {
		return document.select("a[href]");
	}

}