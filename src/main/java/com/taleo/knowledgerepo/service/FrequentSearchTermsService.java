package com.taleo.knowledgerepo.service;

import java.util.List;

import com.taleo.knowledgerepo.model.FrequentSearchTerms;

public interface FrequentSearchTermsService {

	List<FrequentSearchTerms> findFrequentSearchTerms(int threshold);

	List<FrequentSearchTerms> findFrequentSearchTermsBy(String keyword);

	void update(FrequentSearchTerms freqSearchTerms);

	void save(FrequentSearchTerms freqSearchTerms);

}
