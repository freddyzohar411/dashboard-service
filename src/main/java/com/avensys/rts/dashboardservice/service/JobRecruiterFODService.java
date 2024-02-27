package com.avensys.rts.dashboardservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.avensys.rts.dashboardservice.util.UserUtil;
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

	@Autowired
	private UserUtil userUtil;

	/**
	 * New Jobs count
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public Integer getNewJobsCount() throws ServiceException {
		List<Long> userIds = new ArrayList<>();
//		if (!isGetAll) {
			userIds = userUtil.getUsersIdUnderManager();
//		}
//		Optional<Integer> entityOptional = jobRecruiterFODRepository.getNewJobsCount();
		Optional<Integer> entityOptional = jobRecruiterFODRepository.getNewJobsCount(userIds);
		try {
			if (entityOptional.isPresent()) {
				return entityOptional.get();
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage());
		}
	}

	public Integer getActiveJobsCount() throws ServiceException {
		List<Long> userIds = new ArrayList<>();
		//		if (!isGetAll) {
		userIds = userUtil.getUsersIdUnderManager();
		//		}
		Optional<Integer> entityOptional = jobRecruiterFODRepository.getActiveJobsCount(userIds);
		try {
			if (entityOptional.isPresent()) {
				return entityOptional.get();
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage());
		}
	}

	public Integer getInactiveJobsCount() throws ServiceException {
		Optional<Integer> entityOptional = jobRecruiterFODRepository.getInactiveJobsCount();
		try {
			if (entityOptional.isPresent()) {
				return entityOptional.get();
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage());
		}
	}

	public Integer getClosedJobsCount() throws ServiceException {
		Optional<Integer> entityOptional = jobRecruiterFODRepository.getClosedJobsCount();
		try {
			if (entityOptional.isPresent()) {
				return entityOptional.get();
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage());
		}
	}

	public Integer getAssignedJobsCount(Long userId) throws ServiceException {
		Optional<Integer> entityOptional = jobRecruiterFODRepository.getAssignedJobsCount(userId);
		try {
			if (entityOptional.isPresent()) {
				return entityOptional.get();
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage());
		}
	}

	public Integer getFODCount(Long userId) throws ServiceException {
		Optional<Integer> entityOptional = jobRecruiterFODRepository.getFODCount(userId);
		try {
			if (entityOptional.isPresent()) {
				return entityOptional.get();
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage());
		}
	}

	public Integer getAllJobsCount() throws ServiceException {
		Optional<Integer> entityOptional = jobRecruiterFODRepository.getAllJobsCount();
		try {
			if (entityOptional.isPresent()) {
				return entityOptional.get();
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage());
		}
	}

	public Integer getTotalAssignedJobsCount() throws ServiceException {
		Optional<Integer> entityOptional = jobRecruiterFODRepository.getTotalAssignedJobsCount();
		try {
			if (entityOptional.isPresent()) {
				return entityOptional.get();
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage());
		}
	}

	public Integer getTotalFODJobsCount() throws ServiceException {
		Optional<Integer> entityOptional = jobRecruiterFODRepository.getTotalFODJobsCount();
		try {
			if (entityOptional.isPresent()) {
				return entityOptional.get();
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage());
		}
	}

}
