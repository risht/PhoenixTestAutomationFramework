package com.api.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter {

	private static final Logger LOGGER = LogManager.getLogger(SensitiveDataFilter.class);
	
	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {

		System.out.println("=============Hello from the filter!!!==========");
		
		redactPayload(requestSpec);
		
		Response response= ctx.next(requestSpec, responseSpec);//Making the Request
		
		readactResponseBody(response);
		
		System.out.println("==========I got the response in filter ========");
		return response;
	}
	
		//Create a method which is going to READACT/Hide the password from request payload
	
		
		public void redactPayload(FilterableRequestSpecification requestSpec)
		{
			
		String requestPayload = 	requestSpec.getBody().toString();
		
		//to hide the payload
		
		requestPayload =	requestPayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"", "\"password\":\"[READACTED]\"");
		//System.out.println(requestPayload);
		
		LOGGER.info("REQUEST PAYLOAD : {} ",requestPayload);
		
		}

		
		private void readactResponseBody(Response response) {
			
			String responseBody =	response.asPrettyString();
			
			responseBody= responseBody.replaceAll("\"token\"\s*:\s*\"[^\"]+\"", "\"token\":\"[READACTED]\"");
		
			System.out.println(responseBody);
			
			LOGGER.info("RESPONSE BODY : {} ",responseBody);
			
			}



}
