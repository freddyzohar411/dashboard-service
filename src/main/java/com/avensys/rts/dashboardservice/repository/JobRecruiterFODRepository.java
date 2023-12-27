package com.avensys.rts.dashboardservice.repository;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avensys.rts.dashboardservice.entity.JobRecruiterFODEntity;

public interface JobRecruiterFODRepository extends JpaRepository<JobRecruiterFODEntity, Long> {

	@Query(value = "select (select count(id) from job where job.is_deleted  = false) as new_job, "
			+ "(select count(id) from job where is_active = true and job.is_deleted  = false) as active_jobs, "
			+ "(select count(id) from job where is_active = false and job.is_deleted  = false) as inactive_jobs, "
			+ "(select count(id) from job where is_deleted = true) as closed_jobs, "
			+ "(select count(id) from job_recruiter_fod) as assigned_jobs, "
			+ "(select count(id) from job_recruiter_fod where recruiter_id = ?1 or seller_id = ?1 and created_at >= current_date) as fod, "
			+ "(select count(id) from job) as all_jobs, "
			+ "(select count(id) from job_recruiter_fod) as total_assigned_jobs, "
			+ "(select count(id) from job_recruiter_fod where created_at >= current_date) as total_fod", nativeQuery = true)
	Optional<Map<?, ?>> getJobCounts(Long userId);
}