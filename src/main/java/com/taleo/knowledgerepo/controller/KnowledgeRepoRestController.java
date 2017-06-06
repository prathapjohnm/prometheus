package com.taleo.knowledgerepo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taleo.knowledgerepo.model.SearchResult;
import com.taleo.knowledgerepo.service.KnowledgeRepoCrawlerExecService;
import com.taleo.knowledgerepo.service.SearchResultService;

@RestController
public class KnowledgeRepoRestController {

	@Autowired
	SearchResultService searchService;
	
	@Autowired
	KnowledgeRepoCrawlerExecService executorService;

	@RequestMapping(value = "/searchResults/", method = RequestMethod.GET)
	public ResponseEntity<List<SearchResult>> fetchAllSearchResults() {
		List<SearchResult> searchResults = searchService.findAllSearchResults();
		if (searchResults.isEmpty()) {
			return new ResponseEntity<List<SearchResult>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<SearchResult>>(searchResults, HttpStatus.OK);
	}

	@RequestMapping(value = "/searchResults", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SearchResult>> fetchSeachResultsBy(@RequestParam("pageId") int pageId,
			@RequestParam("keyword") String keyword) {
		List<SearchResult> searchResults = searchService.findAllSearchResultsByKeyword(pageId, keyword);
		if (searchResults.isEmpty()) {
			return new ResponseEntity<List<SearchResult>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<SearchResult>>(searchResults, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/executeCrawler/", method = RequestMethod.GET)
	public ResponseEntity<String> executeCrawler() {
		executorService.execute();
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}