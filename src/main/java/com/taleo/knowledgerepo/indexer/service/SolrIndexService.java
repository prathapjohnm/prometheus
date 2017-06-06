package com.taleo.knowledgerepo.indexer.service;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import com.taleo.knowledgerepo.indexer.IndexConstants;
import com.taleo.knowledgerepo.indexer.model.IndexDoc;

@Service
public class SolrIndexService{
	
	public void doIndexation(IndexDoc idxDoc){
		SolrInputDocument solrDoc = getSolrInputDocument(idxDoc);
		
		try {
			getSolrClient().add(solrDoc);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SolrInputDocument getSolrInputDocument(IndexDoc idxDoc) {
		  SolrInputDocument doc = new SolrInputDocument();
	      doc.addField(IndexConstants.DOCID, idxDoc.getDocid());
	      doc.addField(IndexConstants.DOCURL,idxDoc.getDocurl());
	      doc.addField(IndexConstants.DOCTYPE, idxDoc.getDoctype());
	      doc.addField(IndexConstants.DOCKEYWORDS, idxDoc.getDockeywords());
	      
	      return doc;
	}

	public SolrClient getSolrClient(){
		return new HttpSolrClient.Builder("http://blr00bqn:8983/solr/KRCORE/").build();
		
	}
}
