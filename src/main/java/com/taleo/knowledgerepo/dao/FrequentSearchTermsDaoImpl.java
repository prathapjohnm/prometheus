package com.taleo.knowledgerepo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.taleo.knowledgerepo.model.FrequentSearchTerms;

@Repository("frequentSearchTermsDao")
public class FrequentSearchTermsDaoImpl extends AbstractDao<Integer, FrequentSearchTerms>
		implements FrequentSearchTermsDao {

	@Override
	public List<FrequentSearchTerms> findFrequentSearchTerms(Integer threshold) {
		Criteria cr = createEntityCriteria();
		cr.add(Restrictions.ge("hits", threshold));
		return (List<FrequentSearchTerms>) cr.list();
	}

	@Override
	public List<FrequentSearchTerms> findFrequentSearchTermsBy(String keyword) {
		Criteria cr = createEntityCriteria();
		cr.add(Restrictions.eq("keyword", keyword).ignoreCase());
		return (List<FrequentSearchTerms>) cr.list();
	}

	@Override
	public void update(FrequentSearchTerms freqSearchTerms) {
		updateEntity(freqSearchTerms);
	}

	@Override
	public void save(FrequentSearchTerms freqSearchTerms) {
		persist(freqSearchTerms);
	}

}
