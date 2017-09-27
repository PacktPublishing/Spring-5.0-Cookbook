package org.packt.starter.config.webxml;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.packt.starter.config.context.SpringContextConfig;
import org.packt.starter.config.dispatcher.SpringDispatcherConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringWebInitializer implements WebApplicationInitializer {
	  
	  @Override
	  public void onStartup(ServletContext container) throws ServletException {
	    useRootContext(container);
	    useDispatcherContext(container);
	   
	  }

	private void useRootContext(ServletContext container) {
	    // Create the 'root' Spring application context
	    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
	    rootContext.register(SpringContextConfig.class); // <-- Use RootConfig.java
	 
	    // Manage the lifecycle of the root application context
	    container.addListener(new ContextLoaderListener(rootContext));
	  }
	   
	  private void useDispatcherContext(ServletContext container) {
	    // Create the dispatcher servlet's Spring application context
	    AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
	    dispatcherContext.register(SpringDispatcherConfig.class); // <-- Use DispatcherConfig.java
	 
	    // Define mapping between <servlet> and <servlet-mapping>
	    ServletRegistration.Dynamic dispatcher = container.addServlet("spring-dispatcher", new DispatcherServlet(
	        dispatcherContext));
	    dispatcher.addMapping("/");
	    dispatcher.setLoadOnStartup(1);
	  }

}
