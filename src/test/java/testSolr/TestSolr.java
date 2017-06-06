package testSolr;

import com.taleo.knowledgerepo.indexer.model.IndexDoc;
import com.taleo.knowledgerepo.indexer.service.SolrIndexService;

public class TestSolr {

	public static void main(String args[]){
		SolrIndexService srv = new SolrIndexService();
		IndexDoc idxDoc = new IndexDoc();
		idxDoc.setDocid(new Integer(1002));
		idxDoc.setDocurl("http://taleo-infraportal.ca.oracle.com/node/438");
		idxDoc.setDockeywords("Taleo InfraPortal Functional Components");
		idxDoc.setDoctype("Page");
		
		srv.doIndexation(idxDoc);
	}
}
