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

import com.taleo.knowledgerepo.model.FrequentSearchTerms;
import com.taleo.knowledgerepo.model.SearchResultListDetails;
import com.taleo.knowledgerepo.service.FrequentSearchTermsService;
import com.taleo.knowledgerepo.service.KnowledgeRepoCrawlerExecService;
import com.taleo.knowledgerepo.service.SearchResultService;

@RestController
public class KnowledgeRepoRestController {

	@Autowired
	SearchResultService searchService;

	@Autowired
	KnowledgeRepoCrawlerExecService executorService;
	
	@Autowired
	FrequentSearchTermsService frequentSearchTermsService;

	@RequestMapping(value = "/searchResults", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SearchResultListDetails> fetchSeachResultsBy(@RequestParam("pageId") int pageId,
			@RequestParam("keyword") String keyword) {
		SearchResultListDetails searchResultListDetails = searchService.findAllSearchResultsByKeyword(pageId, keyword);
		return new ResponseEntity<SearchResultListDetails>(searchResultListDetails, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/frequentSearchTerms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FrequentSearchTerms>> fetchFrequentSearchTerms() {
		List<FrequentSearchTerms> freqSearchTerms = frequentSearchTermsService.findFrequentSearchTerms(5);
		return new ResponseEntity<List<FrequentSearchTerms>>(freqSearchTerms, HttpStatus.OK);
	}

	@RequestMapping(value = "/executeCrawler/", method = RequestMethod.GET)
	public ResponseEntity<String> executeCrawler() {
		executorService.execute();
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}