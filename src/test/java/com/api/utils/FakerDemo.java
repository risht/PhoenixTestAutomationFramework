package com.api.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class FakerDemo {

	public static void main(String[] args) {

		//We will be using Faker library for our fake test data creation!!!
		
		//We will be creating fakerutil that uses faker library!!
		
		Faker faker = new Faker(new Locale("en-Ind"));
		
		String firstname = faker.name().firstName();
		
		String lastname = faker.name().lastName();
		
		System.out.println(firstname);
		System.out.println(lastname);
		
		String buildingNumber=faker.address().buildingNumber();
		System.out.println(buildingNumber);
		
		System.out.println(faker.address().streetAddress());
		System.out.println(faker.address().streetName());
		
		System.out.println(faker.address().city());
		
		System.out.println(faker.number().digits(500));
		
		System.out.println(faker.numerify("704#########"));
		
		System.out.println(faker.internet().emailAddress());
		
		//System.out.println(faker.phoneNumber().cellPhone());
		
	}
	
}
