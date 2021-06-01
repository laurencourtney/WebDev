package com.lcourtn5.json;

import java.util.Set;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}
	
	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(com.lcourtn5.json.EndPoint.class);
		resources.add(JacksonFeature.class);
	}
}
