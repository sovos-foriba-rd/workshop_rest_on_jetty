package com.foriba.jetty.rs.calculation;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.JsonObject;

@Path("/calculator")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class Calculator {

	@GET
	@Path("/calc/{op}/{left}/{right}")
	@Produces("application/json")
	public ResponseBuilder calculate(@PathParam("op") String op, @PathParam("left") int left, @PathParam("right") int right) {
		Calculation result = new Calculation(op,left,right);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("result",doCalc(result));
		ResponseBuilder response = Response.notModified();
		response.entity(jsonObject.toString()).header("Mehmet", "X").build();

		return response;
//		return jsonObject.toString();
//		return doCalc(result);
	}
	
	@POST
	@Path("/calc/{number}")
	@Produces("application/json")
	public String sendNumber(@PathParam("number") String number) {
		System.out.println("Number: "+number);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("result","Status Code 200");
		
//		return "Status:-> Green <-	Code: 200";
		return jsonObject.toString();
		
	}
	
	private String doCalc(Calculation calculation) {
		String op = calculation.getOperation();
		int left = calculation.getLeft();
		int right = calculation.getRight();
			if (op.equalsIgnoreCase("subtract")) {
				calculation.setResult(left - right);
			} else if (op.equalsIgnoreCase("multiply")) {
				calculation.setResult(left * right);
			} else if (op.equalsIgnoreCase("divide")) {
				calculation.setResult(left / right);
			} else if(op.equalsIgnoreCase("addition")){
				calculation.setResult(left + right);
			} else {
				return "The Parameter of arithmetic operation's wrong. ( "+calculation.getOperation()+" )";
			}
		return calculation.toString();
	}
}
