package org.packt.microservice.core.controller;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.packt.microservice.core.handler.LoginHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class LoginFuncReactControllers {
	
	@Autowired
	private LoginHandler loginHandler;
	
		
	@Bean
	public RouterFunction<ServerResponse> loginServiceBox() {
	    return route(GET("/listFluxLogins"), loginHandler::loginDetailsList)
	    		   .andRoute(GET("/selectLoginById/{id}"), loginHandler::loginDetailsById)
	    		   .andRoute(POST("/selectFluxLogins"), loginHandler::chooseFluxLoginDetails)
	    		   .andRoute(POST("/saveLogin"), loginHandler::saveLogindetailsMono)
	    		   .andRoute(GET("/totalLogins"), loginHandler::countLogins);
	}
	
	@Bean
	public RouterFunction<ServerResponse> userServiceBox() {
	    return route(GET("/listFluxUsers"), loginHandler::userDetailsList)
	    		   .andRoute(GET("/selectUserById/{id}"), loginHandler::userDetailsById)
	    		   .andRoute(POST("/selectFluxUsers"), loginHandler::chooseFluxUserDetails)
	    		   .andRoute(POST("/saveUser"), loginHandler::saveUserdetailsMono)
	    		   .andRoute(GET("/selectUserByFirstName/{fname}"), loginHandler::chooseUserByFirstName)
	    		   .andRoute(GET("/selectUserByLastName/{lname}"), loginHandler::chooseUserByLastName);
	}
	
	
}
