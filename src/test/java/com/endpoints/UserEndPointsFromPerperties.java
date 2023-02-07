package com.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import com.payload.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPointsFromPerperties {
	
	static ResourceBundle getUrls()
	{
		ResourceBundle bundle = ResourceBundle.getBundle("urls"); // urls = properties file(only name we need to mention)
		return bundle;
	}

	public static Response createUser(User payload)
	{
		String postUrl = getUrls().getString("post_url");
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(postUrl);
		
		return response;
	}
	
	public static Response getUser(String username)
	{
		String getUrl = getUrls().getString("get_url");
		Response response = given()
			.pathParam("username", username)
		.when()
			.get(getUrl);
		
		return response;
	}
	
	public static Response updateUser(User payload, String userName)
	{
		String updateUrl = getUrls().getString("update_url");
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(updateUrl);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		String deleteUrl = getUrls().getString("delete_url");
		Response response = given()
			.pathParam("username", userName)
		.when()
			.delete(deleteUrl);
		
		return response;
	}
}
