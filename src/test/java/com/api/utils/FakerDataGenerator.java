package com.api.utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.models.CreateJobPayload;
import com.api.request.models.Customer;
import com.api.request.models.CustomerAddress;
import com.api.request.models.CustomerProduct;
import com.api.request.models.Problems;
import com.github.javafaker.Faker;

public class FakerDataGenerator {
	
	private static Faker faker = new Faker(new Locale("en-ind"));
	
	private static final String COUNTRY="India";
	
	private static final Random RANDOM = new Random();
	
	private static int MST_SERVICE_LOCATION_ID = 0;
	
	private static int MST_PLATFORM_ID = 2;
	
	private static int MST_WARRANTY_STATUS_ID = 1;
	
	private static int MST_OEM_ID = 1;
	
	private static int PRODUCT_ID=1;
	
	private static int MST_MODEL_ID=1;
	
	private FakerDataGenerator() {
		
	}
	
	public static CreateJobPayload generateFakeCreateJobData() {		
		Customer customer = generateFakeCustomerData();
		CustomerAddress customeraddress = generateFakeCustomerAddressData();
		CustomerProduct customerproduct = generateFakeCustomerProduct();	
		List<Problems> problemsList = generateFakeProblemsList();
		CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customeraddress, customerproduct, problemsList);
		return payload;
		
	}

	   public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {		
		
		List<CreateJobPayload> payloadlist = new ArrayList<CreateJobPayload>();
		
		for(int i=1;i<=count;i++) {
		   
		   
		Customer customer = generateFakeCustomerData();
		CustomerAddress customeraddress = generateFakeCustomerAddressData();
		CustomerProduct customerproduct = generateFakeCustomerProduct();	
		List<Problems> problemsList = generateFakeProblemsList();
		CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customeraddress, customerproduct, problemsList);
		payloadlist.add(payload);
		
		}
		
		return payloadlist.iterator();
		
	}


	private static Customer generateFakeCustomerData() {
		
		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String mobileNumber= faker.numerify("70##########");
		String altmobileNumber= faker.numerify("70##########");
		String customeremailAddress= faker.internet().emailAddress();
		String altcustomeremailAddress= faker.internet().emailAddress();
		
		
		Customer customer = new Customer(fname, lname, mobileNumber, altmobileNumber, customeremailAddress, altcustomeremailAddress); 
		return customer;

	}


	private static CustomerAddress generateFakeCustomerAddressData() {
		

		String flatNumber = faker.numerify("###");
		String apartmentName= faker.address().streetName();
		String streetName= faker.address().streetName();
		String landmark= faker.address().streetName();
		String area= faker.address().streetName();
		String pincode= faker.numerify("#####");
		
		String state= faker.address().state();
		
		CustomerAddress customeraddress = new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area,pincode,COUNTRY,state);

		return customeraddress;
		
	}
	
	private static CustomerProduct generateFakeCustomerProduct() {

		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String imeiSerialNumber = faker.numerify("##############");
		String popurl = faker.internet().url();
		
		CustomerProduct customerproduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber, popurl, PRODUCT_ID, MST_MODEL_ID);
		
		return customerproduct ;
	}
	

	private static List<Problems> generateFakeProblemsList() {
		
		int problemId=RANDOM.nextInt(25)+1;//Zero does not come into picture
		
		String fakeremark= faker.lorem().sentence(5);
		
		Problems problems = new Problems(problemId, fakeremark);
		
		List<Problems> problemList = new ArrayList<Problems>();
		
		problemList.add(problems);
		
		System.out.println(problems);
		
		return problemList;
	
	}
	
}
