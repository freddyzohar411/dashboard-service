package com.avensys.rts.dashboardservice.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import com.avensys.rts.dashboardservice.entity.JobEntity;

/***
 * 
 * @author Rahul Sahu
 *
 */
public class JobSpecificationBuilder {

	List<SearchCriteria> params;

	public JobSpecificationBuilder() {
		params = new ArrayList<>();
	}

	public JobSpecificationBuilder with(String key, String operation, String value) {
		params.add(new SearchCriteria(key, operation, value));
		return this;
	}

	public Specification<JobEntity> build() {
		if (params.size() == 0) {
			return null;
		}

		List<Specification<JobEntity>> specs = params.stream().map(SearchSpecifications::new)
				.collect(Collectors.toList());

		Specification<JobEntity> result = specs.get(0);

		for (int i = 1; i < params.size(); i++) {
			result = Specification.where(result).or(specs.get(i));
		}
		return result;
	}

}
