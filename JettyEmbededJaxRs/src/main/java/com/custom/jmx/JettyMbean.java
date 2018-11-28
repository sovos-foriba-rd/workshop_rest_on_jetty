package com.custom.jmx;

import org.eclipse.jetty.util.annotation.ManagedAttribute;
import org.eclipse.jetty.util.annotation.ManagedObject;
import org.eclipse.jetty.util.annotation.ManagedOperation;
import org.eclipse.jetty.util.annotation.Name;

@ManagedObject("Test MBean Annotations")
public class JettyMbean {
	String fname = "Full Name";

	@ManagedAttribute(value = "The full name of something", name = "Jetty")
	public String getFullName() {
		return fname;
	}
	
	@ManagedOperation("Doodle something")
	public void setFullName(@Name(value = "name", description = "set full name") String name) {
		fname = name;
	}

	@ManagedOperation("Doodle something")
	public void doodle(@Name(value = "doodle", description = "A description of the argument") String doodle) {
		System.out.println("doodle " + doodle);
	}
}