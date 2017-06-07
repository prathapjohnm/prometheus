package com.taleo.knowledgerepo.searcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.taleo.knowledgerepo.indexer.IndexConstants;

@Service
@PropertySource(value = { "classpath:seedurls.properties" })
public class SolrSearchService {

	@Autowired
	Environment env;

	public List<Integer> doQuery(String params) {
		List<Integer> solrDocResults = new ArrayList<Integer>();
		SolrQuery query = new SolrQuery();
		query.setQuery("dockeywords:" + params + " doctitle:" + params + "^100");
		query.setFields(IndexConstants.DOCID);
		//query.addSort(IndexConstants.DOCURL, SolrQuery.ORDER.asc);

		QueryResponse response = null;
		try {
			response = getSolrClient().query(query);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response != null) {
			SolrDocumentList results = response.getResults();
			Object docId = null;
			for (SolrDocument solrDocument : results) {
				docId = solrDocument.getFieldValue(IndexConstants.DOCID);
				if (docId != null) {
					solrDocResults.add((Integer) docId);
				}
			}
		}

		return solrDocResults;
	}

	public SolrClient getSolrClient() {
		return new HttpSolrClient.Builder(env.getProperty("solrurl")).build();
	}
}
