package org.packt.microservice.instance.controller;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.packt.microservice.instance.handler.EmpDataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class EmpReactFuncController {
	
	@Autowired
	private EmpDataHandler dataHandler;
	
		
	@Bean
	public RouterFunction<ServerResponse> employeeServiceBox() {
	    return route(GET("/listFluxEmps"), dataHandler::empList)
	    		   .andRoute(GET("/selectEmpById/{id}"), dataHandler::chooseEmpById)
	    		   .andRoute(POST("/selectFluxEmps"), dataHandler::chooseFluxEmps)
	    		   .andRoute(POST("/saveEmp"), dataHandler::saveEmployeeMono)
	    		   .andRoute(GET("/avgAgeEmps"), dataHandler::averageAge)
	    		   .andRoute(GET("/totalAgeEmps"), dataHandler::totalAge)
	    		   .andRoute(GET("/countEmps"), dataHandler::countEmps)
	    		   .andRoute(GET("/countPerDept/{deptid}"), dataHandler::countEmpsPerDept)
	    		   .andRoute(GET("/selectEmpValidAge/{age}"), dataHandler::chooseFluxEmpsValidAge);
	}
	
	
}
