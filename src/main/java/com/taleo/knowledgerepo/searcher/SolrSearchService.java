/*package com.taleo.knowledgerepo.searcher;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

public class SolrSearchService {
	
	public void doQuery(){
		SolrQuery query = new SolrQuery();
		query.setQuery("sony digital camera");
		query.addFilterQuery("cat:electronics","store:amazon.com");
		query.setFields("id","price","merchant","cat","store");
		query.setStart(0);
		query.set("defType", "edismax");

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
		if(response!=null){
			SolrDocumentList results = response.getResults();
			for (int i = 0; i < results.size(); ++i) {
				System.out.println(results.get(i));
			}
		}
    }


	public SolrClient getSolrClient(){
		return new HttpSolrClient.Builder("http://localhost:8983/solr/KRCORE/").build();
		
	}
}
*/