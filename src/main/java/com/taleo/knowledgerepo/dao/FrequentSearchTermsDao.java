package com.taleo.knowledgerepo.dao;

import java.util.List;

import com.taleo.knowledgerepo.model.FrequentSearchTerms;

public interface FrequentSearchTermsDao {

	List<FrequentSearchTerms> findFrequentSearchTerms(Integer threshold);

	List<FrequentSearchTerms> findFrequentSearchTermsBy(String keyword);

	void update(FrequentSearchTerms freqSearchTerms);

	void save(FrequentSearchTerms freqSearchTerms);

}
