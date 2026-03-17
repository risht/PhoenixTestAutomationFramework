package com.api.utils;

import static com.api.constant.Roles.ENG;
import static com.api.constant.Roles.FD;
import static com.api.constant.Roles.QC;
import static com.api.constant.Roles.SUP;
import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.api.constant.Roles;
import com.api.request.models.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	
private static Map<Roles, String> tokenCache = new ConcurrentHashMap<Roles,String>();	
	
private AuthTokenProvider(){
	
}
	
	public static String getToken(Roles roles){
		
		
		if(tokenCache.containsKey(roles))
		{
			return tokenCache.get(roles);
		}
		
		
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
		
		
		tokenCache.put(roles, token);
		return token;
	}

}
