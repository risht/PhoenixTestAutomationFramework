package com.api.utils;

import static com.api.constant.Roles.ENG;
import static com.api.constant.Roles.FD;
import static com.api.constant.Roles.QC;
import static com.api.constant.Roles.SUP;
import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Roles;
import com.api.request.models.UserCredentials;
import com.api.services.AuthService;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	
private static Map<Roles, String> tokenCache = new ConcurrentHashMap<Roles,String>();	

private static final Logger LOGGER = LogManager.getLogger(AuthTokenProvider.class);  

private AuthTokenProvider(){
	
}
	
	public static String getToken(Roles roles){
		
		LOGGER.info("Checking if the token for {} is present in the cache ",roles);
		if(tokenCache.containsKey(roles))
		{
			LOGGER.info("token found for {} ",roles);
			return tokenCache.get(roles);
		}
		LOGGER.info("token not found making the logging request for {} ", roles);
		
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
		
		LOGGER.info("token chache for future request");
		
		tokenCache.put(roles, token);
		return token;
	}

}
