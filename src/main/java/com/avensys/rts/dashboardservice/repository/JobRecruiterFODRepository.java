package com.avensys.rts.dashboardservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avensys.rts.dashboardservice.entity.JobRecruiterFODEntity;

public interface JobRecruiterFODRepository extends JpaRepository<JobRecruiterFODEntity, Long> {

	@Query(value = "select count(id) from job where id not in (select fod.job_id from job_recruiter_fod fod)", nativeQuery = true)
	Optional<Integer> getNewJobsCount();

	@Query(value = "select count(id) from job where is_active = true and is_deleted  = false", nativeQuery = true)
	Optional<Integer> getActiveJobsCount();

	@Query(value = "select count(id) from job where is_active = false and is_deleted  = false", nativeQuery = true)
	Optional<Integer> getInactiveJobsCount();

	@Query(value = "select count(fod.job_id) from job_recruiter_fod fod where fod.status = 'CLOSED'", nativeQuery = true)
	Optional<Integer> getClosedJobsCount();

	@Query(value = "select count(id) from job_recruiter_fod where status != 'CLOSED' and (recruiter_id = ?1 or sales_id = ?1)", nativeQuery = true)
	Optional<Integer> getAssignedJobsCount(Long userId);

	@Query(value = "select count(id) from job_recruiter_fod where recruiter_id = ?1 or sales_id = ?1 and created_at >= current_date", nativeQuery = true)
	Optional<Integer> getFODCount(Long userId);

	@Query(value = "select count(id) from job where is_deleted  = false", nativeQuery = true)
	Optional<Integer> getAllJobsCount();

	@Query(value = "select count(id) from job_recruiter_fod where status != 'CLOSED'", nativeQuery = true)
	Optional<Integer> getTotalAssignedJobsCount();

	@Query(value = "select count(id) from job_recruiter_fod where created_at >= current_date", nativeQuery = true)
	Optional<Integer> getTotalFODJobsCount();
}