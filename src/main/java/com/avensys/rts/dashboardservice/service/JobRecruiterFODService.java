package com.avensys.rts.dashboardservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avensys.rts.dashboardservice.exception.ServiceException;
import com.avensys.rts.dashboardservice.repository.JobRecruiterFODRepository;
import com.avensys.rts.dashboardservice.util.UserUtil;

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
	public Integer getNewJobsCount(Boolean getAll) throws ServiceException {
		try {
			List<Long> userIds = new ArrayList<>();
			userIds = userUtil.getUsersIdUnderManager();
			Optional<Integer> entityOptional = Optional.empty();
			if (getAll) {
				entityOptional = jobRecruiterFODRepository.getNewJobsCountAll();
			} else {
				entityOptional = jobRecruiterFODRepository.getNewJobsCount(userIds);
			}
			if (entityOptional.isPresent()) {
				return entityOptional.get();
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage());
		}
	}

	public Integer getActiveJobsCount(Boolean getAll) throws ServiceException {
		try {
			List<Long> userIds = new ArrayList<>();
			userIds = userUtil.getUsersIdUnderManager();
			Optional<Integer> entityOptional = Optional.empty();
			if (getAll) {
				entityOptional = jobRecruiterFODRepository.getActiveJobsCountAll();
			} else {
				entityOptional = jobRecruiterFODRepository.getActiveJobsCount(userIds);
			}
			if (entityOptional.isPresent()) {
				return entityOptional.get();
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage());
		}
	}

	public Integer getInactiveJobsCount(Boolean getAll) throws ServiceException {
		try {
			List<Long> userIds = new ArrayList<>();
			userIds = userUtil.getUsersIdUnderManager();
			Optional<Integer> entityOptional = Optional.empty();
			if (getAll) {
				entityOptional = jobRecruiterFODRepository.getInactiveJobsCountAll();
			} else {
				entityOptional = jobRecruiterFODRepository.getInactiveJobsCount(userIds);
			}
			if (entityOptional.isPresent()) {
				return entityOptional.get();
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage());
		}
	}

	public Integer getClosedJobsCount(Boolean getAll) throws ServiceException {
		try {
			List<Long> userIds = new ArrayList<>();
			userIds = userUtil.getUsersIdUnderManager();
			Optional<Integer> entityOptional = Optional.empty();
			if (getAll) {
				entityOptional = jobRecruiterFODRepository.getClosedJobsCountAll();
			} else {
				entityOptional = jobRecruiterFODRepository.getClosedJobsCount(userIds);
			}
			if (entityOptional.isPresent()) {
				return entityOptional.get();
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage());
		}
	}

	public Integer getAssignedJobsCount(Boolean getAll) throws ServiceException {
		try {
			List<Long> userIds = new ArrayList<>();
			userIds = userUtil.getUsersIdUnderManager();
			Optional<Integer> entityOptional = Optional.empty();
			if (getAll) {
				entityOptional = jobRecruiterFODRepository.getAssignedJobsCountAll();
			} else {
				entityOptional = jobRecruiterFODRepository.getAssignedJobsCount(userIds);
			}
			if (entityOptional.isPresent()) {
				return entityOptional.get();
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage());
		}
	}

	public Integer getFODCount(Boolean getAll) throws ServiceException {
		try {
			List<Long> userIds = new ArrayList<>();
			userIds = userUtil.getUsersIdUnderManager();
			Optional<Integer> entityOptional = Optional.empty();
			if (getAll) {
				entityOptional = jobRecruiterFODRepository.getFODCountAll();
			} else {
				entityOptional = jobRecruiterFODRepository.getFODCount(userIds);
			}
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
		try {
			Optional<Integer> entityOptional = jobRecruiterFODRepository.getAllJobsCount();
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
