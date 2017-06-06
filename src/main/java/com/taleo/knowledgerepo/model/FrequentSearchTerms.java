package com.taleo.knowledgerepo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KRFREQUENTSEARCHTERMS")
public class FrequentSearchTerms implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String keyword;

	private int hits;

	public FrequentSearchTerms() {

	}

	public FrequentSearchTerms(int count, String keyword) {
		this.hits = count;
		this.keyword = keyword;
	}

	@Override
	public int hashCode() {
		final int prime = 71;
		int result = 1;
		result = prime * keyword.hashCode() * id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FrequentSearchTerms))
			return false;
		FrequentSearchTerms other = (FrequentSearchTerms) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FrequentSearchTerms [id =" + id + "+keyword=" + keyword + ", count=" + hits + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hitcount) {
		this.hits = hitcount;
	}
	
	public void incrementHitCount(){
		this.hits = ++ hits;
	}

}
