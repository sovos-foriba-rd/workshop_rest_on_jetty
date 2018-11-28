package com.foriba.jetty.rs;

import java.lang.management.ManagementFactory;

import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.log.Log;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.custom.jmx.JettyMbean;

public class Main  {
	public static void main(String[] args) throws Exception {
		Server server = new Server(2222);
		ServletContextHandler context = new ServletContextHandler(server, "/example");
		
		ResourceConfig config = new ResourceConfig();
		config.packages("com.foriba.jetty.rs");
		
		ServletHolder servlet = new ServletHolder(new ServletContainer(config));
		context.addServlet(servlet, "/*");
		
		// Setup JMX
		MBeanContainer mbContainer=new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
		server.addEventListener(mbContainer);
		
		server.addBean(mbContainer);
		server.addBean(new JettyMbean());
		
		server.addBean(Log.getLog());
		try {
			server.start();
			server.join();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


}
