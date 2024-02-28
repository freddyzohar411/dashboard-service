package com.avensys.rts.dashboardservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.avensys.rts.dashboardservice.annotation.RequiresAllPermissions;
import com.avensys.rts.dashboardservice.constant.MessageConstants;
import com.avensys.rts.dashboardservice.enums.Permission;
import com.avensys.rts.dashboardservice.exception.ServiceException;
import com.avensys.rts.dashboardservice.payload.JobListingRequestDTO;
import com.avensys.rts.dashboardservice.service.JobRecruiterFODService;
import com.avensys.rts.dashboardservice.service.JobService;
import com.avensys.rts.dashboardservice.util.JwtUtil;
import com.avensys.rts.dashboardservice.util.ResponseUtil;

/**
 * @author Rahul Sahu This class used to perform all the dashboard operations
 * 
 */
@CrossOrigin
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

	private static final Logger LOG = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	private JobRecruiterFODService jobRecruiterFODService;

	@Autowired
	private JobService jobService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private MessageSource messageSource;

	@RequiresAllPermissions({ Permission.JOB_READ })
	@GetMapping("/newjobs")
	public ResponseEntity<?> getNewJobsCount(@RequestHeader(name = "Authorization") String token) {
		LOG.info("getNewJobsCount request received");
		try {
			Integer count = jobRecruiterFODService.getNewJobsCount();
			return ResponseUtil.generateSuccessResponse(count, HttpStatus.OK,
					messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
		} catch (ServiceException e) {
			return ResponseUtil.generateSuccessResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

//	@RequiresAllPermissions({ Permission.JOB_READ })
//	@GetMapping("/newjobs")
//	public ResponseEntity<?> getNewJobsCount(
//			@RequestParam(value = "isGetAll", required = false, defaultValue = "false") Boolean isGetAll,
//			@RequestHeader(name = "Authorization") String token) {
//		LOG.info("getNewJobsCount request received");
//		try {
//			Integer count = jobRecruiterFODService.getNewJobsCount();
//			return ResponseUtil.generateSuccessResponse(count, HttpStatus.OK,
//					messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
//		} catch (ServiceException e) {
//			return ResponseUtil.generateSuccessResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
//		}
//	}

	@RequiresAllPermissions({ Permission.JOB_READ })
	@GetMapping("/activejobs")
	public ResponseEntity<?> getActiveJobsCount(@RequestHeader(name = "Authorization") String token) {
		LOG.info("getActiveJobsCount request received");
		try {
			Integer count = jobRecruiterFODService.getActiveJobsCount();
			return ResponseUtil.generateSuccessResponse(count, HttpStatus.OK,
					messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
		} catch (ServiceException e) {
			return ResponseUtil.generateSuccessResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@RequiresAllPermissions({ Permission.JOB_READ })
	@GetMapping("/inactivejobs")
	public ResponseEntity<?> getInactiveJobsCount(@RequestHeader(name = "Authorization") String token) {
		LOG.info("getInactiveJobsCount request received");
		try {
			Integer count = jobRecruiterFODService.getInactiveJobsCount();
			return ResponseUtil.generateSuccessResponse(count, HttpStatus.OK,
					messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
		} catch (ServiceException e) {
			return ResponseUtil.generateSuccessResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@RequiresAllPermissions({ Permission.JOB_READ })
	@GetMapping("/closedjobs")
	public ResponseEntity<?> getClosedJobsCount(@RequestHeader(name = "Authorization") String token) {
		LOG.info("getClosedJobsCount request received");
		try {
			Integer count = jobRecruiterFODService.getClosedJobsCount();
			return ResponseUtil.generateSuccessResponse(count, HttpStatus.OK,
					messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
		} catch (ServiceException e) {
			return ResponseUtil.generateSuccessResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@RequiresAllPermissions({ Permission.JOB_READ })
	@GetMapping("/assignedjobs")
	public ResponseEntity<?> getAssignedJobsCount(@RequestHeader(name = "Authorization") String token) {
		LOG.info("getAssignedJobsCount request received");
		try {
			Long userId = jwtUtil.getUserId(token);
			Integer count = jobRecruiterFODService.getAssignedJobsCount(userId);
			return ResponseUtil.generateSuccessResponse(count, HttpStatus.OK,
					messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
		} catch (ServiceException e) {
			return ResponseUtil.generateSuccessResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@RequiresAllPermissions({ Permission.JOB_READ })
	@GetMapping("/fod")
	public ResponseEntity<?> getFODCount(@RequestHeader(name = "Authorization") String token) {
		LOG.info("getFODCount request received");
		try {
			Long userId = jwtUtil.getUserId(token);
			Integer count = jobRecruiterFODService.getFODCount(userId);
			return ResponseUtil.generateSuccessResponse(count, HttpStatus.OK,
					messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
		} catch (ServiceException e) {
			return ResponseUtil.generateSuccessResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@RequiresAllPermissions({ Permission.JOB_READ })
	@GetMapping("/alljobs")
	public ResponseEntity<?> getAllJobsCount(@RequestHeader(name = "Authorization") String token) {
		LOG.info("getAllJobsCount request received");
		try {
			Integer count = jobRecruiterFODService.getAllJobsCount();
			return ResponseUtil.generateSuccessResponse(count, HttpStatus.OK,
					messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
		} catch (ServiceException e) {
			return ResponseUtil.generateSuccessResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@RequiresAllPermissions({ Permission.JOB_READ })
	@GetMapping("/totalassignedjobs")
	public ResponseEntity<?> getTotalAssignedJobsCount(@RequestHeader(name = "Authorization") String token) {
		LOG.info("getTotalAssignedJobsCount request received");
		try {
			Integer count = jobRecruiterFODService.getTotalAssignedJobsCount();
			return ResponseUtil.generateSuccessResponse(count, HttpStatus.OK,
					messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
		} catch (ServiceException e) {
			return ResponseUtil.generateSuccessResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@RequiresAllPermissions({ Permission.JOB_READ })
	@GetMapping("/totalfodjobs")
	public ResponseEntity<?> getTotalFODJobsCount(@RequestHeader(name = "Authorization") String token) {
		LOG.info("getTotalFODJobsCount request received");
		try {
			Integer count = jobRecruiterFODService.getTotalFODJobsCount();
			return ResponseUtil.generateSuccessResponse(count, HttpStatus.OK,
					messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
		} catch (ServiceException e) {
			return ResponseUtil.generateSuccessResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PostMapping("/jobfod/listing")
	public ResponseEntity<Object> getAccountListing(@RequestBody JobListingRequestDTO jobListingRequestDTO,
			@RequestHeader(name = "Authorization") String token) {
		Long userId = jwtUtil.getUserId(token);
		Integer page = jobListingRequestDTO.getPage();
		Integer pageSize = jobListingRequestDTO.getPageSize();
		String sortBy = jobListingRequestDTO.getSortBy();
		String sortDirection = jobListingRequestDTO.getSortDirection();
		String searchTerm = jobListingRequestDTO.getSearchTerm();
		List<String> searchFields = jobListingRequestDTO.getSearchFields();
		if (searchTerm == null || searchTerm.isEmpty()) {
			return ResponseUtil.generateSuccessResponse(
					jobService.getJobListingPage(page, pageSize, sortBy, sortDirection, userId), HttpStatus.OK,
					messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
		} else {
			return ResponseUtil.generateSuccessResponse(
					jobService.getJobListingPageWithSearch(page, pageSize, sortBy, sortDirection, searchTerm,
							searchFields, userId),
					HttpStatus.OK,
					messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
		}
	}
}
