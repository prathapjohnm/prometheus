package com.taleo.knowledgerepo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.taleo.knowledgerepo.crawler.KnowledgeRepoCrawler;

@PropertySource(value = { "classpath:seedurls.properties" })
@Service
public class KnowledgeRepoCrawlerExecService {

	@Autowired
	Environment env;
	
	@Autowired
	KnowledgeRepoCrawler knowledgeRepoCrawler;

	public void execute() {
		String[] urlsToVisit = env.getRequiredProperty("tovisit").split(",");

		for (String urls : urlsToVisit) {
			knowledgeRepoCrawler.visitPageLinks(urls, 0);
		}
	}

}
