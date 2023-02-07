package com.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.endpoints.UserEndPointsFromPerperties;
import com.github.javafaker.Faker;
import com.payload.User;

import io.restassured.response.Response;

public class TestUserFromProperties {

	Faker faker;
	User userPayload;
	public Logger logger ;
	
	@BeforeClass
	public void setUpData()
	{
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUserName(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	public void testCreateUser()
	{
		logger.info("******* creating user *********");
		Response response = UserEndPointsFromPerperties.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******* User is created *********");
	}
	
	@Test(priority = 2)
	public void testGetUser()
	{
		logger.info("******* getting user *********");
		Response response  = UserEndPointsFromPerperties.getUser(this.userPayload.getUserName());
		response.then().log().all();
//		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("******* user details *********");
	}
	
	@Test(priority = 3)
	public void testUpdateUser()
	{
		userPayload.setUserName(faker.name().fullName());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		
		Response response = UserEndPointsFromPerperties.updateUser(userPayload, this.userPayload.getUserName());
		response.then().log().all();
//		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response responseAfterUpdate = UserEndPointsFromPerperties.getUser(this.userPayload.getUserName());
		responseAfterUpdate.then().log().all();
	}
	
	@Test(priority = 4)
	public void testDeleteUser()
	{
		Response response = UserEndPointsFromPerperties.deleteUser(this.userPayload.getUserName());
//		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
