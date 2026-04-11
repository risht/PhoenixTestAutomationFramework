package com.api.services;

import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Roles;
import com.api.request.models.CreateJobPayload;
import com.api.utils.SpecUtils;

import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT="/job/create";
	
	private static final String SEARCH_ENDPOINT = "/job/search";
	
	private static final Logger LOGGER = LogManager.getLogger(JobService.class);  
	
	public Response createJob(Roles roles,CreateJobPayload createJobPayload)

	{
		LOGGER.info("Making request to {} with role {} and payload {} ",CREATE_JOB_ENDPOINT,roles,createJobPayload);
		
		return given()
		.spec(requestSpecWithAuth(roles, createJobPayload))
		.when()
		.post(CREATE_JOB_ENDPOINT);
	}
	
	
	public Response seacrhJob(Roles roles,Object payload)

	{
		LOGGER.info("Making request to {} with role {} and payload {} ",SEARCH_ENDPOINT,roles,payload);
		return given()
		.spec(SpecUtils.requestSpecWithAuth(roles))
		.body(payload)
		.post(SEARCH_ENDPOINT);
		
	}
}
