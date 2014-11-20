package com.walmart.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

public class HttpClient {
	private HttpURLConnection conn;
	private static HttpClient instance;
	private URL url;
	private static final Logger LOGGER = Logger.getLogger(HttpClient.class);

	private HttpClient(String urlToRead) {
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			LOGGER.error("Can't open HTTP connection");
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static HttpClient getInstance(String urlToRead) {
		instance = new HttpClient(urlToRead);
		return instance;
	}

	public int getResponseCode() throws IOException {
		conn.setRequestMethod("GET");
		conn.connect();
		return conn.getResponseCode();
	}

	public String getRequest() {
		return formatJsonRequest(sendRequest("GET"));
	}

	public String postRequest() {
		return formatJsonRequest(sendRequest("POST"));
	}

	public static String formatJsonRequest(String body) {
		return body.replace("[", "").replace("]", "");
	}

	public String sendRequest(String method) {
		BufferedReader rd;
		String line;
		String result = "";
		try {
			conn.setRequestMethod(method);
			rd = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (Exception e) {
			LOGGER.info("Can't send request via Http connection");
			throw new RuntimeException(e.getMessage(), e);
		}
		return result;
	}

}
