package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	// 1.GET Method without header

	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);// http get request
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget); // hit the get url
		return closeableHttpResponse;

	}

	// 2.GET Method with headers
	public CloseableHttpResponse get(String url,HashMap<String,String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);// http get request
		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget); // hit the get url
		return closeableHttpResponse;
}
	//3.POST Method
	public CloseableHttpResponse post(String url,String entityString,HashMap<String,String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);//http post request
		httppost.setEntity(new StringEntity(entityString)); //for payload
		
		//for headers
		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httppost); // hit the post url
		return closeableHttpResponse;
	}
	
	
}
