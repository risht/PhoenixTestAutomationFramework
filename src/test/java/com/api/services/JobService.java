package com.api.services;

import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constant.Roles;
import com.api.request.models.CreateJobPayload;

import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT="/job/create";
	
	public Response createJob(Roles roles,CreateJobPayload createJobPayload)

	{
		return given()
		.spec(requestSpecWithAuth(roles, createJobPayload))
		.when()
		.post(CREATE_JOB_ENDPOINT);
	}
	
}
