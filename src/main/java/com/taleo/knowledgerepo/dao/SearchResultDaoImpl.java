package com.taleo.knowledgerepo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.taleo.knowledgerepo.model.SearchResult;

@Repository("searchResultDao")
public class SearchResultDaoImpl extends AbstractDao<Integer, SearchResult> implements SearchResultDao {

	public SearchResult findById(int id) {
		SearchResult searchResult = getByKey(id);

		return searchResult;
	}

	@Override
	public List<SearchResult> findAllSearchResults() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<SearchResult> searchResults = (List<SearchResult>) criteria.list();
		return searchResults;
	}

	@Override
	public void save(SearchResult searchResult) {
		persist(searchResult);
	}
	
	

}
