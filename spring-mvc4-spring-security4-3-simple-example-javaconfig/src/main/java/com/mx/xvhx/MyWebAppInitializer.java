package com.mx.xvhx;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebAppInitializer implements WebApplicationInitializer {
	
	@Override
	public void onStartup(ServletContext container) throws ServletException {
		
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		
		// registering a configuration class
		rootContext.register(AppConfig.class, WebAppSecurityConfig.class);
		
		// adding a listener to the ServletContext for load the context
		container.addListener(new ContextLoaderListener(rootContext));
		
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		
		// registering a configuration class
		webContext.register(WebAppMvcConfig.class);
		
		// or setting an entire package for configuration classes
		// context.setConfigLocation("com.example.app.config");
		
		ServletRegistration.Dynamic dispatcher = container
			.addServlet("dispatcherServlet", new DispatcherServlet(webContext));
		
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
		container.addFilter("springSecurityFilterChain",
			new DelegatingFilterProxy("springSecurityFilterChain"))
				.addMappingForUrlPatterns(null, false, "/*");
		
	}
}
