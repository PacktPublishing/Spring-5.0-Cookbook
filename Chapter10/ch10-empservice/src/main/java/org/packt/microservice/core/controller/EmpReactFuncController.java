package org.packt.microservice.core.controller;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.packt.microservice.core.handler.EmpDataHandler;
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
public class EmpReactFuncController {
	
	@Autowired
	private EmpDataHandler empDataHandler;
	
		
	@Bean
	public RouterFunction<ServerResponse> employeeServiceBox() {
	    return route(GET("/listFluxEmps"), empDataHandler::empList)
	    		   .andRoute(GET("/selectEmpById/{id}"), empDataHandler::chooseEmpById)
	    		   .andRoute(POST("/selectFluxEmps"), empDataHandler::chooseFluxEmps)
	    		   .andRoute(POST("/saveEmp"), empDataHandler::saveEmployeeMono)
	    		   .andRoute(GET("/avgAgeEmps"), empDataHandler::averageAge)
	    		   .andRoute(GET("/totalAgeEmps"), empDataHandler::totalAge)
	    		   .andRoute(GET("/countEmps"), empDataHandler::countEmps)
	    		   .andRoute(GET("/hello"), empDataHandler::fluxHello)
	    		   .andRoute(GET("/countPerDept/{deptid}"), empDataHandler::countEmpsPerDept)
	    		   .andRoute(GET("/selectEmpValidAge/{age}"), empDataHandler::chooseFluxEmpsValidAge);
	}
}
