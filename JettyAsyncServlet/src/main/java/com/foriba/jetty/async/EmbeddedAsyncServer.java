package com.foriba.jetty.async;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.foriba.jetty.async.EmbededAsyncServlet.EmbeddedAsyncServlet;

public class EmbeddedAsyncServer {
	
	public static void main(String[] args) throws Exception {
		Server server = new Server(9090);
		ServletContextHandler context = new ServletContextHandler();
		context.setContextPath("/JettyAsyncServlet");
		ServletHolder asyncHolder = context.addServlet(EmbeddedAsyncServlet.class, "/async");
		asyncHolder.setAsyncSupported(false);
		server.setHandler(context);
		server.start();
		server.join();
	}

}
