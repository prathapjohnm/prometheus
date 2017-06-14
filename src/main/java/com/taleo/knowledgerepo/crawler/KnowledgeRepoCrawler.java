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
import java.util.HashMap;

@Component
public class KnowledgeRepoCrawler {

	@Autowired
	URLVisitInfo urlInfo;
	
	@Autowired
	SearchResultAssembler searchResultAssembler;
	
	@Autowired
	DocumentIndexer docIndexer;

	String workSpace;

	HashMap<String,String> ckMap = new HashMap<String, String>();
	
	public static final String CONFLUENCE = "confluence";
	public static final String INFRAPORTAL = "infraportal";
	
	public KnowledgeRepoCrawler() {

	}

	/*
	 * 
	 */
	public void visitPageLinks(String URL, int depth) {
		String strDomainName = getDomainName(URL);
		if(strDomainName.equals(INFRAPORTAL)){
			visitPageLinksInfra(URL,depth);
		} else if(strDomainName.equals(CONFLUENCE)){
			setupConfluenceCrawl();
			workSpace = getWorkSpaceName(URL);
			visitPageLinksConfluence(URL, depth);
		}
    }
	
	
	private void setupConfluenceCrawl() {
		ckMap.put("JSESSIONID","AC8A0C7163DB3949A233C6DDBEAA1DE3");
		ckMap.put("OHS-confluence.oraclecorp.com-80",
				  "6AAA41E6B26AC1E9184A3582930453DE80C9987D005B56E9A5FE252D77FEBD1D7712631738C0661D4FA3F6A6E49BDAF97DB4A0846DC899BA0B2C4C6113A7F008E2CF90563DA7C545A8287C3DAAABC6B807177C44DE92B5EC0F275858D04CA613139950FFF935D809938F6E6396BB9EC2FE3CDD32F6AADC5BF04EC196E1C6057170D4FE7FCE3072091A3659F1C5409F8725D4D9A42A176F57D9FF1C3EF34B80ADC4664F51C6B37F6F8A4A13BC136489AE92F65DFB0C212E72F65B074E3B72FE55E6C15F9CF6A0DBC988C093B11A575B236BCBD797A8E067B350CC1369D4692A64EAA9A1B6AD45FCB1E59EB4B21F095383~"
				);
		ckMap.put("BIGipServerconfluence.oraclecorp.com_pool","431637656.25374.0000");
		
	}

	public void visitPageLinksInfra(String URL, int depth) {
		try {
			urlInfo.prepareURLsToAvoid();
			if (urlInfo.isRequiredToVisit(URL, depth)) {
				System.out.println(">> shouldVisit : true");

    			Document document = Jsoup.connect(URL).get();
    			//String testString = document.select("body").text();
    			Elements linksOnPage = getLinksOnPageInfra(document);
    			//TODO
    			/* send the document to DAO and Service to sync data to lookup table */
    			Integer iDocId = saveSearchResultEntity(document,URL);
    			//TODO
    			/* send the document to indexer for data scraping and indexation */
    			sendDocForIndexation(document,URL,iDocId);
    			
    			depth++;
    			for (Element page : linksOnPage) {
    				visitPageLinksInfra(page.attr("abs:href"), depth);
    			}
    		}
    	} catch (IOException e) {
    		System.err.println("For '" + URL + "': " + e.getMessage());
    	}
    }

	public void visitPageLinksConfluence(String URL, int depth) {
		try {
			urlInfo.prepareURLsToAvoid();
			if (urlInfo.isRequiredToVisit(URL, depth)) {
				System.out.println(">> shouldVisit : true");

    			Document document = Jsoup.connect(URL).cookies(ckMap).get();;
    			//String testString = document.select("body").text();
    			Elements linksOnPage = getLinksOnPageConfluence(document,workSpace);
    			//TODO
    			/* send the document to DAO and Service to sync data to lookup table */
    			Integer iDocId = saveSearchResultEntity(document,URL);
    			//TODO
    			/* send the document to indexer for data scraping and indexation */
    			sendDocForIndexation(document,URL,iDocId);
    			
    			depth++;
    			for (Element page : linksOnPage) {
    				visitPageLinksConfluence(page.attr("abs:href"), depth);
    			}
    		}
    	} catch (IOException e) {
    		System.err.println("For '" + URL + "': " + e.getMessage());
    	}
    }
	
	private Elements getLinksOnPageConfluence(Document document, String strWrkSpc) {
		Elements el = document.select("div#main");
		Element e1 = el.get(0);
		Elements elRet = e1.select("a[href*=/display/"+strWrkSpc+"/]");
		return elRet;
	}

	private void sendDocForIndexation(Document document, String uRL, Integer iDocId) {
		docIndexer.indexDocument(uRL, iDocId,"Page", DocumentTransformationUtil.getKeywordsForIndexer(document), document.title());
	}
	private Integer saveSearchResultEntity(Document document, String uRL) {
		SearchResult srcResult = searchResultAssembler.assembleAndSave(document, uRL);
		return srcResult.getId();
	}

	private Elements getLinksOnPageInfra(Document document) {
		return document.select("a[href]");
	}

	private String getDomainName(String strURL){
		if(strURL.contains("//confluence.oraclecorp.com")){
			return CONFLUENCE;
		} else if(strURL.contains("//taleo-infraportal.ca.oracle.com")){
			return INFRAPORTAL;
		}
	    return "";
	}
	
	private String getWorkSpaceName(String uRL) {
		String strSpc = uRL.substring(uRL.indexOf("key="));
		strSpc = strSpc.replace("key=","");
		return strSpc;
	}
}