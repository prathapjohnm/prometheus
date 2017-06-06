package com.taleo.knowledgerepo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taleo.knowledgerepo.model.SearchResult;
import com.taleo.knowledgerepo.service.SearchResultService;

@RestController
public class KnowledgeRepoRestController {

	@Autowired
	SearchResultService userService;

	@RequestMapping(value = "/searchResults/", method = RequestMethod.GET)
	public ResponseEntity<List<SearchResult>> listAllUsers() {
		List<SearchResult> users = userService.findAllSearchResults();
		if (users.isEmpty()) {
			return new ResponseEntity<List<SearchResult>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<SearchResult>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/searchResults/{pageid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SearchResult>> getUser(@PathVariable("pageid") long id) {
		List<SearchResult> users = userService.findAllSearchResults();
		if (users.isEmpty()) {
			return new ResponseEntity<List<SearchResult>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<SearchResult>>(users, HttpStatus.OK);
	}

}