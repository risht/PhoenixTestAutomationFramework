package com.api.filters;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter {

	private static final Logger LOGGER = LogManager.getLogger(SensitiveDataFilter.class);
	
	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {

		//System.out.println("=============Hello from the filter!!!==========");
		
		LOGGER.info("**************REQUEST DETAILS ************");
		
		LOGGER.info("BASE URI : {} ",requestSpec.getURI());
		
		LOGGER.info("HTTP METHOD : {} ",requestSpec.getMethod());
		
		//Headers
		
		redactHeader(requestSpec);
		
		
		//requestSpec.getHeaders();
		
		
		redactPayload(requestSpec);
		
		Response response= ctx.next(requestSpec, responseSpec);//Making the Request
		
		LOGGER.info("**************RESPONSE DETAILS ************");
		
		LOGGER.info("STATUS : {} ",response.getStatusLine());
		
		LOGGER.info("RESPONSE TIME ms : {} ",response.timeIn(TimeUnit.MILLISECONDS));
		
		LOGGER.info("RESPONSE HEADERS  : \n {} ",response.getHeaders());
		
		readactResponseBody(response);
		
		//System.out.println("==========I got the response in filter ========");
		return response;
	}
	
		private void redactHeader(FilterableRequestSpecification requestSpec) {
		
			List<Header>headerList = requestSpec.getHeaders().asList();
			
			for(Header h : headerList) 
			{	
				if(h.getName().equalsIgnoreCase("Authorization")) {
				LOGGER.info("HEADER KEY {} : VALUE{}" ,h.getName() ,"\"[REDACTED]\"");
			}else {
				LOGGER.info("HEADER KEY {} : VALUE{}",h.getName() ,h.getValue());
			}
			
			}
			
		
	}

		//Create a method which is going to READACT/Hide the password from request payload
	
		
		public void redactPayload(FilterableRequestSpecification requestSpec)
		{
			
		if(requestSpec.getBody()!=null)
		{
			
		//ONly for POST,PUT AND DEL PAYLOAD  	
		String requestPayload = 	requestSpec.getBody().toString();
		
		//to hide the payload
		
		requestPayload =	requestPayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"", "\"password\":\"[READACTED]\"");
		//System.out.println(requestPayload);
		
		LOGGER.info("REQUEST PAYLOAD : \n {} ",requestPayload);
		
		}

		}
		
		private void readactResponseBody(Response response) {
			
			String responseBody =	response.asPrettyString();
			
			responseBody= responseBody.replaceAll("\"token\"\s*:\s*\"[^\"]+\"", "\"token\":\"[READACTED]\"");
		
			System.out.println(responseBody);
			
			LOGGER.info("RESPONSE BODY : {} ",responseBody);
			
			}



}
