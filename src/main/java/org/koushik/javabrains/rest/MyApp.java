package org.koushik.javabrains.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("webapi")
public class MyApp extends Application {
	public Set<java.lang.Class<?>> getClasses() {
		return new HashSet<Class<?>>();
	}
}