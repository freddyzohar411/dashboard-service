package com.avensys.rts.dashboardservice.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avensys.rts.dashboardservice.exception.ServiceException;
import com.avensys.rts.dashboardservice.repository.JobRecruiterFODRepository;

/**
 * @author Rahul Sahu
 * 
 */
@Service
public class JobRecruiterFODService {

	@Autowired
	private JobRecruiterFODRepository jobRecruiterFODRepository;

	/**
	 * @description Get Job counts for dashboard
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public Map<?, ?> getJobFOD(Long userId) throws ServiceException {
		Optional<Map<?, ?>> entityOptional = jobRecruiterFODRepository.getJobCounts(userId);
		try {
			if (entityOptional.isPresent()) {
				return entityOptional.get();
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage());
		}
	}

}
