package com.avensys.rts.dashboardservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avensys.rts.dashboardservice.entity.JobRecruiterFODEntity;

public interface JobRecruiterFODRepository extends JpaRepository<JobRecruiterFODEntity, Long> {
	
	// Add filter only in list of user Id
	@Query(value = "select count(id) from job where id not in (select distinct(fod.job_id) from job_recruiter_fod fod) and is_active = true and is_deleted = false and created_by IN :userIds", nativeQuery = true)
	Optional<Integer> getNewJobsCount(List<Long> userIds);

	@Query(value = "select count(id) from job where id not in (select distinct(fod.job_id) from job_recruiter_fod fod) and is_active = true and is_deleted = false", nativeQuery = true)
	Optional<Integer> getNewJobsCountAll();

	@Query(value = "select count(distinct(job_id)) from job_recruiter_fod where status != 'CLOSED' and (recruiter_id IN :userIds or sales_id IN :userIds)", nativeQuery = true)
	Optional<Integer> getActiveJobsCount(List<Long> userIds);

	@Query(value = "select count(distinct(job_id)) from job_recruiter_fod where status != 'CLOSED'", nativeQuery = true)
	Optional<Integer> getActiveJobsCountAll();

	@Query(value = "select count(id) from job where is_active = false and is_deleted  = false", nativeQuery = true)
	Optional<Integer> getInactiveJobsCount();

	@Query(value = "select count(distinct(fod.job_id)) from job_recruiter_fod fod where fod.status = 'CLOSED'", nativeQuery = true)
	Optional<Integer> getClosedJobsCount();

	@Query(value = "select count(distinct(job_id)) from job_recruiter_fod where status != 'CLOSED' and (recruiter_id IN :userIds or sales_id IN :userIds)", nativeQuery = true)
	Optional<Integer> getAssignedJobsCount(List<Long> userIds);

	@Query(value = "select count(distinct(job_id)) from job_recruiter_fod where (recruiter_id IN :userIds or sales_id IN :userIds) and created_at >= current_date", nativeQuery = true)
	Optional<Integer> getFODCount(List<Long> userIds);

	@Query(value = "select count(distinct(job_id)) from job_recruiter_fod where created_at >= current_date", nativeQuery = true)
	Optional<Integer> getFODCountAll();

	@Query(value = "select count(id) from job where is_deleted  = false", nativeQuery = true)
	Optional<Integer> getAllJobsCount();

	@Query(value = "select count(distinct(job_id)) from job_recruiter_fod where status != 'CLOSED'", nativeQuery = true)
	Optional<Integer> getTotalAssignedJobsCount();

	@Query(value = "select count(distinct(job_id)) from job_recruiter_fod where created_at >= current_date", nativeQuery = true)
	Optional<Integer> getTotalFODJobsCount();
}