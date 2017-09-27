package org.packt.microservice.core.controller;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.packt.microservice.core.handler.DeptDataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class DeptReactFuncController {
	
	@Autowired
	private DeptDataHandler dataHandler;
	
		
	@Bean
	public RouterFunction<ServerResponse> departmentServiceBox(){
		return route(GET("/listFluxDepts"), dataHandler::deptList)
				.andRoute(GET("/selectDeptById/{id}"), dataHandler::chooseDeptById)
				.andRoute(POST("/selectFluxDepts"), dataHandler::chooseFluxDepts)
				.andRoute(POST("/saveFluxDept"), dataHandler::saveDepartmentMono)
				.andRoute(GET("/countFluxDepts"), dataHandler::countDepts);
		
	}
}
