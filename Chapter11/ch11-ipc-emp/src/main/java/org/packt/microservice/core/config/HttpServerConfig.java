package org.packt.microservice.core.config;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.http.server.reactive.ServletHttpHandlerAdapter;
import org.springframework.web.reactive.DispatcherHandler;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import reactor.ipc.netty.NettyContext;
import reactor.ipc.netty.http.server.HttpServer;

@Configuration
@EnableWebFlux
public class HttpServerConfig {
	
	
	@Bean
	public  NettyContext nettyContext(ApplicationContext context) {
		HttpHandler handler = DispatcherHandler.toHttpHandler(context);
		ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
		HttpServer httpServer = HttpServer.create("localhost", Integer.valueOf("8908"));
		return httpServer.newHandler(adapter).block();
	}
	
	
	 // This is another option just in case the embedded Tomcat is used but needs more customization
    public ServletRegistrationBean routeServlet1(RouterFunction<?> routerFunction) throws Exception {
	 	HttpHandler httpHandler = RouterFunctions.toHttpHandler(routerFunction );
	 	ServletHttpHandlerAdapter servlet = new ServletHttpHandlerAdapter(httpHandler);
	 	
        ServletRegistrationBean registrationBean = new ServletRegistrationBean<>(servlet, "/flux" + "/*");
        registrationBean.setLoadOnStartup(1);
        registrationBean.setAsyncSupported(true);
        
    	System.out.println("starts server");		
        return registrationBean;
    }

	
	
	
	
	

}
