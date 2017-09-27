package org.packt.spring.boot.controller;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.packt.spring.boot.handler.DataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFlux
public class ReactiveControllers {
	
	@Autowired
	private DataHandler dataHandler;
	
	@Bean
	public RouterFunction<?> routeMono() {
        return   route(GET("/mono" + "/stream"), routeMonoHandle());
    }
	
	public HandlerFunction<ServerResponse> routeMonoHandle(){
		HandlerFunction<ServerResponse> handlerMono =
				request -> ok().body(Mono.just("Mono Stream"), String.class);
		    return handlerMono;
	}
	
	@Bean
	public RouterFunction<ServerResponse> handledRoute(){
		RouterFunction<ServerResponse> router = route(GET("/routeFluxHandle"), handlerFluxData());
		
		return router;
	}
	public HandlerFunction<ServerResponse> handlerFluxData(){
		HandlerFunction<ServerResponse> handlerFlux =
			    req -> ServerResponse.ok().body(fromObject("Flux Stream from String"));
		    return handlerFlux;
	}
	
		   
	@Bean
	public RouterFunction<ServerResponse> compundRoutes() {
	       return route(GET("/routeFlux"), dataHandler::fluxHello)
	              .andRoute(GET("/stream"), dataHandler::stream);
	    }
	
	
	@Bean
	public RouterFunction<ServerResponse> listAllEmps() {
	       return route(GET("/routeEmps"), dataHandler::empList);
	    }
}
