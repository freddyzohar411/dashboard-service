package com.avensys.rts.dashboardservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avensys.rts.dashboardservice.annotation.RequiresAllPermissions;
import com.avensys.rts.dashboardservice.constant.MessageConstants;
import com.avensys.rts.dashboardservice.enums.Permission;
import com.avensys.rts.dashboardservice.exception.ServiceException;
import com.avensys.rts.dashboardservice.service.JobRecruiterFODService;
import com.avensys.rts.dashboardservice.util.JwtUtil;
import com.avensys.rts.dashboardservice.util.ResponseUtil;
import com.avensys.rts.dashboardservice.util.UserUtil;

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
	private UserUtil userUtil;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private JwtUtil jwtUtil;

	@RequiresAllPermissions({ Permission.JOB_READ })
	@GetMapping("/newjobs")
	public ResponseEntity<?> getNewJobsCount(@RequestHeader(name = "Authorization") String token) {
		LOG.info("getNewJobsCount request received");
		try {
			Boolean getAll = userUtil.checkIsAdmin();
			Integer count = jobRecruiterFODService.getNewJobsCount(getAll);
			return ResponseUtil.generateSuccessResponse(count, HttpStatus.OK,
					messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
		} catch (ServiceException e) {
			return ResponseUtil.generateSuccessResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@RequiresAllPermissions({ Permission.JOB_READ })
	@GetMapping("/activejobs")
	public ResponseEntity<?> getActiveJobsCount(@RequestHeader(name = "Authorization") String token) {
		LOG.info("getActiveJobsCount request received");
		try {
			Long userId = jwtUtil.getUserId(token);
			Boolean getAll = userUtil.checkIsAdmin();
			Integer count = jobRecruiterFODService.getActiveJobsCount(getAll, userId);
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
			Boolean getAll = userUtil.checkIsAdmin();
			Integer count = jobRecruiterFODService.getInactiveJobsCount(getAll);
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
			Boolean getAll = userUtil.checkIsAdmin();
			Integer count = jobRecruiterFODService.getClosedJobsCount(getAll);
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
			Boolean getAll = userUtil.checkIsAdmin();
			Integer count = jobRecruiterFODService.getAssignedJobsCount(getAll, userId);
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
			Boolean getAll = userUtil.checkIsAdmin();
			Integer count = jobRecruiterFODService.getFODCount(getAll);
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
			Boolean getAll = userUtil.checkIsAdmin();
			Integer count = jobRecruiterFODService.getAllJobsCount(getAll);
			return ResponseUtil.generateSuccessResponse(count, HttpStatus.OK,
					messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
		} catch (ServiceException e) {
			return ResponseUtil.generateSuccessResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
