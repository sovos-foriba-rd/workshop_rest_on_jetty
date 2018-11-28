package com.foriba.jetty.test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpURLConnectionTest {
	private static final String GET_URL_ASYNC = "http://localhost:9090/JettyAsyncServlet/async";
	private static final String GET_URL_ASYNC2 = "http://localhost:8080/sync";
	
	private static ExecutorService executor = Executors.newFixedThreadPool(4);
	
	public static void main(String[] args) throws IOException {
		
		for (int i = 0; i < 10000; i++) {
			Runnable worker = new RunnableHelper(GET_URL_ASYNC2);
			executor.execute(worker);
		}
		executor.shutdown();
	}
}
