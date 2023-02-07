package com.endpoints;

import com.payload.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserEndPoints {

	public static Response createUser(User payload)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.createUserUrl);
		
		return response;
	}
	
	public static Response getUser(String username)
	{
		Response response = given()
			.pathParam("username", username)
		.when()
			.get(Routes.getUserUrl);
		
		return response;
	}
	
	public static Response updateUser(User payload, String userName)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(Routes.updateUserUrl);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response = given()
			.pathParam("username", userName)
		.when()
			.delete(Routes.deleteUserUrl);
		
		return response;
	}
}
