package com.avensys.rts.dashboardservice.response;

import java.util.List;

import com.avensys.rts.dashboardservice.entity.JobEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobListingResponseDTO {
	private Integer totalPages;
	private Long totalElements;
	private Integer page;
	private Integer pageSize;
	private List<JobEntity> jobs;
}
