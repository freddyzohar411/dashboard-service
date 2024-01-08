package com.avensys.rts.dashboardservice.search;

import org.springframework.data.jpa.domain.Specification;

import com.avensys.rts.dashboardservice.entity.JobEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/***
 * 
 * @author Rahul Sahu
 *
 */
public class SearchSpecifications implements Specification<JobEntity> {

	private static final long serialVersionUID = 114368327468L;

	private SearchCriteria criteria;

	public SearchSpecifications(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<JobEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

		if (criteria.getOperation().equalsIgnoreCase(">")) {
			return builder.greaterThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase("<")) {
			return builder.lessThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase(":")) {
			if (root.get(criteria.getKey()).getJavaType() == String.class) {
				return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
			} else {
				return builder.equal(root.get(criteria.getKey()), criteria.getValue());
			}
		}

		return null;
	}

}
