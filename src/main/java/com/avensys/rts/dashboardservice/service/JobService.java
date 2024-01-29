package com.avensys.rts.dashboardservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.avensys.rts.dashboardservice.entity.JobEntity;
import com.avensys.rts.dashboardservice.exception.ServiceException;
import com.avensys.rts.dashboardservice.repository.JobRepository;
import com.avensys.rts.dashboardservice.response.JobListingResponseDTO;
import com.avensys.rts.dashboardservice.search.JobSpecificationBuilder;
import com.avensys.rts.dashboardservice.util.StringUtil;

/**
 * @author Rahul Sahu
 * 
 */
@Service
public class JobService {

	private static final Logger LOG = LoggerFactory.getLogger(JobService.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private JobRepository jobRepository;

	/**
	 * This method is used to retrieve a job Information
	 * 
	 * @param id
	 * @return
	 */
	public JobEntity getById(Long id) throws ServiceException {
		if (id == null) {
			throw new ServiceException(
					messageSource.getMessage("error.provide.id", new Object[] { id }, LocaleContextHolder.getLocale()));
		}

		Optional<JobEntity> permission = jobRepository.findById(id);
		if (permission.isPresent() && !permission.get().getIsDeleted()) {
			return permission.get();
		} else {
			throw new ServiceException(messageSource.getMessage("error.jobnotfound", new Object[] { id },
					LocaleContextHolder.getLocale()));
		}
	}

	/**
	 * This method is used to Delete a job
	 * 
	 * @param id
	 */
	public void delete(Long id) throws ServiceException {
		JobEntity dbJob = getById(id);
		dbJob.setIsDeleted(true);
		jobRepository.save(dbJob);
	}

	public List<JobEntity> getAllJobs(Integer pageNo, Integer pageSize, String sortBy) throws ServiceException {
		LOG.info("getAllJobs request processing");
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
			List<JobEntity> jobEntityList = jobRepository.findAllAndIsDeleted(false, paging);
			LOG.info("jobEntityList retrieved : Service");
			return jobEntityList;
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage());
		}
	}

	public Page<JobEntity> search(String searchTerms, Pageable pageable) throws ServiceException {
		LOG.info("search request processing");
		try {
			JobSpecificationBuilder builder = new JobSpecificationBuilder();
			Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
			Matcher matcher = pattern.matcher(searchTerms + ",");

			while (matcher.find()) {
				builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
			}

			return jobRepository.findAll(builder.build(), pageable);
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage());
		}
	}

	public JobListingResponseDTO getJobListingPage(Integer page, Integer size, String sortBy, String sortDirection,
			Long userId) {
		// Get sort direction
		Sort.Direction direction = Sort.DEFAULT_DIRECTION;
		if (sortDirection != null && !sortDirection.isEmpty()) {
			direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		}
		if (sortBy == null || sortBy.isEmpty() || sortBy.equals("")) {
			sortBy = "updated_at";
			direction = Sort.Direction.DESC;
		}
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));

		Page<JobEntity> accountEntitiesPage = null;
		// Try with numeric first else try with string (jsonb)
		try {
			accountEntitiesPage = jobRepository.findAllByOrderByNumeric(userId, false, false, true, pageRequest);
		} catch (Exception e) {
			accountEntitiesPage = jobRepository.findAllByOrderByString(userId, false, false, true, pageRequest);
		}

		JobListingResponseDTO jobListingResponseDTO = new JobListingResponseDTO();
		jobListingResponseDTO.setTotalElements(accountEntitiesPage.getTotalElements());
		jobListingResponseDTO.setTotalPages(accountEntitiesPage.getTotalPages());
		jobListingResponseDTO.setPage(accountEntitiesPage.getNumber());
		jobListingResponseDTO.setPageSize(accountEntitiesPage.getSize());
		jobListingResponseDTO.setJobs(accountEntitiesPage.getContent());

		return jobListingResponseDTO;
	}

	public JobListingResponseDTO getJobListingPageWithSearch(Integer page, Integer size, String sortBy,
			String sortDirection, String searchTerm, List<String> searchFields, Long userId) {
		// Get sort direction
		Sort.Direction direction = Sort.DEFAULT_DIRECTION;
		if (sortDirection != null) {
			direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		}
		if (sortBy == null) {
			sortBy = "updated_at";
			direction = Sort.Direction.DESC;
		}
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));

		Page<JobEntity> accountEntitiesPage = null;
		// Try with numeric first else try with string (jsonb)
		try {
			accountEntitiesPage = jobRepository.findAllByOrderByAndSearchNumeric(userId, false, false, true,
					pageRequest, searchFields, searchTerm);
		} catch (Exception e) {
			accountEntitiesPage = jobRepository.findAllByOrderByAndSearchString(userId, false, false, true, pageRequest,
					searchFields, searchTerm);
		}

		JobListingResponseDTO jobListingResponseDTO = new JobListingResponseDTO();
		jobListingResponseDTO.setTotalElements(accountEntitiesPage.getTotalElements());
		jobListingResponseDTO.setTotalPages(accountEntitiesPage.getTotalPages());
		jobListingResponseDTO.setPage(accountEntitiesPage.getNumber());
		jobListingResponseDTO.setPageSize(accountEntitiesPage.getSize());
		jobListingResponseDTO.setJobs(accountEntitiesPage.getContent());

		return jobListingResponseDTO;
	}

	public List<Map<String, String>> getAllJobFields(Long userId) throws ServiceException {
		List<JobEntity> jobEntities = jobRepository.findAllByUserAndDeleted(userId, false, true);
		if (jobEntities.isEmpty()) {
			throw new ServiceException(
					messageSource.getMessage("error.norecordfound", null, LocaleContextHolder.getLocale()));
		}

		// Declare a new hashmap to store the label and value
		Map<String, String> keyMap = new HashMap<>();

		// Lets store normal column first
		keyMap.put("Title", "title");
		keyMap.put("Created At", "createdAt");
		keyMap.put("Updated At", "updatedAt");
		keyMap.put("Created By", "createdByName");
		keyMap.put("Updated By", "updatedByName");
		// Loop through the account submission data jsonNode
		for (JobEntity jobEntity : jobEntities) {
			if (jobEntity.getJobSubmissionData() != null) {
				Iterator<String> accountFieldNames = jobEntity.getJobSubmissionData().fieldNames();
				while (accountFieldNames.hasNext()) {
					String fieldName = accountFieldNames.next();
					keyMap.put(StringUtil.convertCamelCaseToTitleCase2(fieldName), "jobSubmissionData." + fieldName);
				}
			}

		}

		List<Map<String, String>> fieldOptions = new ArrayList<>();
		// Loop Through map
		for (Map.Entry<String, String> entry : keyMap.entrySet()) {
			// Create a list of map with label and value
			Map<String, String> map = new HashMap<>();
			map.put("label", entry.getKey());
			map.put("value", entry.getValue());
			if (entry.getValue().contains(".")) {
				String[] split = entry.getValue().split("\\.");
				map.put("sortValue", StringUtil.camelCaseToSnakeCase(split[0]) + "." + split[1]);
			} else {
				map.put("sortValue", StringUtil.camelCaseToSnakeCase(entry.getValue()));
			}
			fieldOptions.add(map);
		}
		return fieldOptions;
	}

	public JobEntity getJobDraft(Long userId) throws ServiceException {
		Optional<JobEntity> jobEntity = jobRepository.findByUserAndDraftAndDeleted(userId, true, false, true);
		try {
			if (jobEntity.isPresent() && !jobEntity.get().getIsDeleted()) {
				return jobEntity.get();
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage());
		}
	}

}