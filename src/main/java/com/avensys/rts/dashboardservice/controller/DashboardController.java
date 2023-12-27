package com.avensys.rts.dashboardservice.controller;

import java.util.Map;

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
	private JwtUtil jwtUtil;

	@Autowired
	private MessageSource messageSource;

	/**
	 * Save a job fod
	 * 
	 * @return
	 */
	@RequiresAllPermissions({ Permission.JOB_READ })
	@GetMapping("/jobfod")
	public ResponseEntity<?> getJobFOD(@RequestHeader(name = "Authorization") String token) {
		LOG.info("JobFOD request received");
		try {
			Long userId = jwtUtil.getUserId(token);
			Map<?, ?> list = jobRecruiterFODService.getJobFOD(userId);
			return ResponseUtil.generateSuccessResponse(list, HttpStatus.OK,
					messageSource.getMessage(MessageConstants.MESSAGE_SUCCESS, null, LocaleContextHolder.getLocale()));
		} catch (ServiceException e) {
			return ResponseUtil.generateSuccessResponse(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
