package com.foriba.jetty.rs.apps;

import org.glassfish.jersey.server.ResourceConfig;

public class Apps extends ResourceConfig  {
    public Apps() {
//        packages("com.foriba.jetty.rs.calculation");
//        packages("com.foriba.jetty.rs.helloworld");
    	  packages("com.foriba.jetty.rs.*");
    }
}
