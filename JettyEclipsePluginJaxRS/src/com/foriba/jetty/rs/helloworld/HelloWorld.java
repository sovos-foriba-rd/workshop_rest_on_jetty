package com.foriba.jetty.rs.helloworld;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class HelloWorld {

	@GET
	@Path("/world")
	public String calculate() {
		return "Hello World!";
	}


}
