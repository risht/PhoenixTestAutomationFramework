package com.api.utils;

import static io.restassured.RestAssured.given;

import static com.api.constant.Roles.*;

import com.api.constant.Roles;
import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	
private AuthTokenProvider(){
	
}
	
	public static String getToken(Roles roles){
		
		UserCredentials userCredentials = null;
		
		if(roles == FD) {
			
			userCredentials = new UserCredentials("iamfd", "password");
		}
		
		else if(roles == SUP)
		{
			userCredentials = new UserCredentials("iamsup", "password");
		}
		

		else if(roles == ENG)
		{
			userCredentials = new UserCredentials("iameng", "password");
		}
		
		
		else if(roles == QC)
		{
			userCredentials = new UserCredentials("iamqc", "password");
		}
		
		String token = given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.body(userCredentials)
		.when()
		.post("login")
		.then()
		.log().ifValidationFails()
		.extract()
		.body()
		.jsonPath()
		.getString("data.token");
		
		//System.out.println(token);
		
		return token;
	}

}
