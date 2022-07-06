package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase {
	public PostAPITest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	TestBase testbase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restclient;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void setUp() throws IOException {
		testbase = new TestBase();
		serviceUrl = pro.getProperty("URL");
		apiUrl = pro.getProperty("serviceURL");
		url = serviceUrl + apiUrl;

	}

	@Test
	public void postAPITest() throws StreamWriteException, DatabindException, IOException {
		restclient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API:
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus", "leader");

		// object to json file;
		mapper.writeValue(
				new File("C:\\Users\\DELL\\eclipse-workspace\\restapi\\src\\main\\java\\com\\qa\\data\\users.json"),
				users);

		// object to json in string
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println(usersJsonString);
		closeableHttpResponse = restclient.post(url, usersJsonString, headerMap);
		// 1.Status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201);

		// 2.jsonString
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is :" + responseJson);

		// json to java object
		Users usersResObj = mapper.readValue(responseString, Users.class);// actual users obj
		System.out.println(usersResObj);
		Assert.assertTrue(users.getName().equals(usersResObj.getName()));
		Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));
		System.out.println(usersResObj.getId());
		System.out.println(usersResObj.getCreatedAt());

	}

}
