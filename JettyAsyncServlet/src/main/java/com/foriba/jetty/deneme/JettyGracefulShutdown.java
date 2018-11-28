package com.foriba.jetty.deneme;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.StatisticsHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class JettyGracefulShutdown {

	private static CountDownLatch latch = new CountDownLatch(1);

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
//		server.setStopTimeout(30000);
		ServletContextHandler contextHandler = new ServletContextHandler();
		contextHandler.setContextPath("/");
		contextHandler.addServlet(AsyncServlet.class, "/async").setAsyncSupported(true);
		contextHandler.addServlet(SyncServlet.class, "/sync").setAsyncSupported(false);
		server.setHandler(contextHandler);
		StatisticsHandler handler = new StatisticsHandler();
		handler.setHandler(server.getHandler());
		server.setHandler(handler);
		server.start();
		System.out.println("Waiting for a request to /sync or /async");
		latch.await();
		System.out.println("Request received. Shutting down");
		long start = System.currentTimeMillis();
//		server.stop();
		long duration = System.currentTimeMillis() - start;
		System.out.println("Shutdown complete in " + duration + "ms");
	}

	public static class AsyncServlet extends HttpServlet {

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			AsyncContext context = req.startAsync();
			context.start(() -> {
				latch.countDown();
				try {
					System.out.println("AsyncServlet");
					Thread.sleep(1000);
				}
				catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				context.complete();
			});
		}

	}

	public static class SyncServlet extends HttpServlet {

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			latch.countDown();
			try {
				System.out.println("SyncServlet");
				Thread.sleep(1000);
			}
			catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}

	}

}
