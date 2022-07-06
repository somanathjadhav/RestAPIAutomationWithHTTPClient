package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Util.TestUtil;
import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetAPITest extends TestBase {
	public GetAPITest() throws IOException {
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

	@Test(priority=1)
	public void getAPITestwithoutHeaders() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		closeableHttpResponse = restclient.get(url);
		// a.status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code -->" + statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200);
		// b.json string
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API-->" + responseJson);
		//single value assertion
		//per_page
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("Value of Per Page is-->" + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		//total
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("Value of total is-->" + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		//get value from JSON Array
		String lastname=TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id=TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar=TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String first_name=TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		System.out.println(lastname);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(first_name);
		// c.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	@Test(priority=2)
	public void getAPITestwithHeaders() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		closeableHttpResponse = restclient.get(url,headerMap);
		// a.status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code -->" + statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200);
		// b.json string
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API-->" + responseJson);
		//single value assertion
		//per_page
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("Value of Per Page is-->" + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		//total
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("Value of total is-->" + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		//get value from JSON Array
		String lastname=TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id=TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar=TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String first_name=TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		System.out.println(lastname);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(first_name);
		// c.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array-->" + allHeaders);

	}

}
