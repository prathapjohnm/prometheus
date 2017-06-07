package com.taleo.knowledgerepo.indexer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taleo.knowledgerepo.indexer.model.IndexDoc;
import com.taleo.knowledgerepo.indexer.service.SolrIndexService;

@Component
public class DocumentIndexer {

	@Autowired
	IndexDoc idxDoc;

	@Autowired
	SolrIndexService idxSrv;

	public void indexDocument(String URL, Integer docId, String docType, String docText, String title) {

		// construct the info to index
		idxDoc.initialize(URL, docId, docType, docText, title);

		idxSrv.doIndexation(idxDoc);

	}

}
