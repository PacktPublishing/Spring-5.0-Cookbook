package org.packt.web.reactor.webxml;

import java.util.EnumSet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionTrackingMode;

import org.packt.web.reactor.config.SpringContextConfig;
import org.packt.web.reactor.dispatch.SpringDispatcherConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan(basePackages = "org.packt.reactive.codes")
@Configuration
public class SpringWebinitializer implements WebApplicationInitializer {
 
	  @Override
	  public void onStartup(ServletContext container) throws ServletException {
	    addRootContext(container);
	    addDispatcherContext(container);
	   
	  }

	private void addRootContext(ServletContext container) {
	    // Create the application context
	    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
	    rootContext.register(SpringContextConfig.class); 
	 
	    // Register application context with ContextLoaderListener
	    container.addListener(new ContextLoaderListener(rootContext));
	   container.setInitParameter("contextConfigLocation", "org.packt.web.reactor.security.config");
	   container.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE)); // if URL, enable sessionManagement URL rewriting   
	 
	  }
	   
	  private void addDispatcherContext(ServletContext container) {
	    // Create the dispatcher servlet's Spring application context
	    AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
	    dispatcherContext.register(SpringDispatcherConfig.class); 
	    
	 
	    // Declare  <servlet> and <servlet-mapping> for the DispatcherServlet
	    ServletRegistration.Dynamic dispatcher = container.addServlet("ch08-servlet", 
	    		new DispatcherServlet(dispatcherContext));
	    dispatcher.addMapping("/");
	    dispatcher.setLoadOnStartup(1);
	    dispatcher.setAsyncSupported(true); 
	    
	    //FilterRegistration.Dynamic springSecurityFilterChain = container.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
      // springSecurityFilterChain.addMappingForUrlPatterns(null, false, "/*");
      // springSecurityFilterChain.setAsyncSupported(true);

	  }
	  
	  
}
