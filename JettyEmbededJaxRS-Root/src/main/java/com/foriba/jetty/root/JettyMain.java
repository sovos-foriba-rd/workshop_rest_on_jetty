package com.foriba.jetty.root;

import com.foriba.jetty.server.JettyServer;

public class JettyMain {
	public static void main(String[] args) {
		try {
			JettyServer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
