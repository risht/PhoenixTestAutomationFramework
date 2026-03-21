package com.api.services;

import static com.api.constant.Roles.FD;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constant.Roles;

import io.restassured.response.Response;

public class UserService {

	private static final String USERDETAILS_ENDPOINT="/userdetails";
	
	public Response userDetails(Roles roles) {	
		
		Response response =given()
		.spec(requestSpecWithAuth(roles))
		.when()
		.log().headers()
		.get(USERDETAILS_ENDPOINT);
		
		return response;
		
		
	}
}
