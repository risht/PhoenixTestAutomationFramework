package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.models.Search;
import com.api.services.JobService;
import com.api.utils.SpecUtils;


@Listeners(com.listeners.APITestListners.class)
public class SearchAPITest {

	private JobService jobService;
	
	private static final String JOB_NUMBER="JOB_186900";
	
	private Search searchPayload;

	@BeforeMethod(description ="Instantiating the JobService and Creating the search Payload")
	public void setUp() {
		jobService = new JobService();
		searchPayload = new Search(JOB_NUMBER);
	}

	@Test(description="Verify if the search api is working properly", groups = {"e2e","smoke","api"})
	public void searchAPITest() {
		jobService.seacrhJob(Roles.FD, searchPayload)
		.then()
		.spec(SpecUtils.responseSpec_OK()).body("message", Matchers.equalTo("Success"));
	}
}
