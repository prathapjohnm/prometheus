package testSolr;

import com.taleo.knowledgerepo.searcher.SolrSearchService;

public class TestSolrQuery {

	public static void main(String args[]){
		SolrSearchService srv = new SolrSearchService();
		srv.doQuery("Akira");
	}
}
