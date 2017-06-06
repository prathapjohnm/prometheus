package com.taleo.knowledgerepo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taleo.knowledgerepo.dao.FrequentSearchTermsDao;
import com.taleo.knowledgerepo.model.FrequentSearchTerms;

@Service("frequentSearchTermsService")
@Transactional
public class FrequentSearchTermsServiceImpl implements FrequentSearchTermsService {

	@Autowired
	FrequentSearchTermsDao freqSearchTermsDao;

	@Override
	public List<FrequentSearchTerms> findFrequentSearchTerms(int threshold) {
		return freqSearchTermsDao.findFrequentSearchTerms(threshold);
	}

	@Override
	public List<FrequentSearchTerms> findFrequentSearchTermsBy(String keyword) {
		return freqSearchTermsDao.findFrequentSearchTermsBy(keyword);
	}

	@Override
	public void update(FrequentSearchTerms freqSearchTerms) {
		freqSearchTermsDao.update(freqSearchTerms);
	}

	@Override
	public void save(FrequentSearchTerms freqSearchTerms) {
		freqSearchTermsDao.save(freqSearchTerms);
	}

}
