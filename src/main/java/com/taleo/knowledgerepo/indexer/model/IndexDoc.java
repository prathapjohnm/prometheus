package com.taleo.knowledgerepo.indexer.model;

import org.springframework.stereotype.Component;

@Component
public class IndexDoc {

	private Integer docid;

	private String docurl;

	private String doctype;

	private String dockeywords;

	public Integer getDocid() {
		return docid;
	}

	public void setDocid(Integer docid) {
		this.docid = docid;
	}

	public String getDocurl() {
		return docurl;
	}

	public void setDocurl(String docurl) {
		this.docurl = docurl;
	}

	public String getDoctype() {
		return doctype;
	}

	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}

	public String getDockeywords() {
		return dockeywords;
	}

	public void setDockeywords(String dockeywords) {
		this.dockeywords = dockeywords;
	}

	public void initialize(String docurl, Integer docId, String doctype, String dockeywords) {
		this.docurl = docurl;
		this.docid = docId;
		this.doctype = doctype;
		this.dockeywords = dockeywords;
	}

}
