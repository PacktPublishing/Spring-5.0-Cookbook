package org.packt.microservice.core.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.packt.microservice.core.model.data.LoginDetails;
import org.packt.microservice.core.model.data.TotalUsers;
import org.packt.microservice.core.model.data.UserDetails;
import org.packt.microservice.core.service.LogindetailsService;
import org.packt.microservice.core.service.UserdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Component
public class LoginHandler {
	
	@Autowired
	private LogindetailsService logindetailsServiceImpl;
	
	@Autowired
	private UserdetailsService userdetailsServiceImpl;
	
	// Login Details
	
	public Mono<ServerResponse> loginDetailsList(ServerRequest req) {
		Flux<LoginDetails> flux = Flux.fromIterable(logindetailsServiceImpl.findAllLogindetails());
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(flux, LoginDetails.class);
	}
	
	public Mono<ServerResponse> loginDetailsById(ServerRequest req) {
		Scheduler subWorker = Schedulers.newSingle("sub-thread");
		Mono<LoginDetails> login = Mono.defer(() -> Mono.justOrEmpty(logindetailsServiceImpl.findLoginById(Integer.parseInt(req.pathVariable("id"))))).subscribeOn(subWorker);
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(login, LoginDetails.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> chooseFluxLoginDetails(ServerRequest req) {
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(req.bodyToFlux(Integer.class).flatMap((id) -> Mono.justOrEmpty(logindetailsServiceImpl.findLoginById(id))), LoginDetails.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	
	public Mono<ServerResponse> chooseFluxLoginUsername(ServerRequest req) {
		Flux<LoginDetails> flux = Flux.fromIterable(logindetailsServiceImpl.findAllLogindetails()).filter((login)-> login.getUsername().contains(req.pathVariable("pattern")));
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(flux, LoginDetails.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	
     public Mono<ServerResponse> countLogins(ServerRequest req) {
		
		Mono<Long> count = Flux.fromIterable(logindetailsServiceImpl.findAllLogindetails())
				.count();
		TotalUsers countEmp = new TotalUsers();
		countEmp.setCount(count.block());
		Mono<TotalUsers> monoCntLogins = Mono.justOrEmpty(countEmp);
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(monoCntLogins, TotalUsers.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
			
    public Mono<ServerResponse> saveLogindetailsMono(ServerRequest req) {
    	Scheduler subWorker = Schedulers.newSingle("sub-thread");
    	Mono<LoginDetails> loginDetails = req.bodyToMono(LoginDetails.class).doOnNext(logindetailsServiceImpl::saveLogindetails).subscribeOn(subWorker);
       	return ok().contentType(MediaType.APPLICATION_STREAM_JSON).build(loginDetails.then());
 	}
    
    // User Details
    public Mono<ServerResponse> userDetailsList(ServerRequest req) {
		Flux<UserDetails> flux = Flux.fromIterable(userdetailsServiceImpl.findAllUserdetails());
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(flux, UserDetails.class);
	}
	
	public Mono<ServerResponse> userDetailsById(ServerRequest req) {
		Scheduler subWorker = Schedulers.newSingle("sub-thread");
		Mono<UserDetails> user = Mono.defer(() -> Mono.justOrEmpty(userdetailsServiceImpl.findUserById(Integer.parseInt(req.pathVariable("id"))))).subscribeOn(subWorker);
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(user, UserDetails.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> chooseFluxUserDetails(ServerRequest req) {
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(req.bodyToFlux(Integer.class).flatMap((id) -> Mono.justOrEmpty(userdetailsServiceImpl.findUserById(id))), UserDetails.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}	
	
	public Mono<ServerResponse> chooseUserByFirstName(ServerRequest req) {
		Flux<UserDetails> flux = Flux.fromIterable(userdetailsServiceImpl.findAllUserdetails()).filter((login)-> login.getFirstname().contains(req.pathVariable("fname")));
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(flux, UserDetails.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> chooseUserByLastName(ServerRequest req) {
		Flux<UserDetails> flux = Flux.fromIterable(userdetailsServiceImpl.findUserByLastname(req.pathVariable("lname")));
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(flux, UserDetails.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
		
    public Mono<ServerResponse> saveUserdetailsMono(ServerRequest req) {
    	Scheduler subWorker = Schedulers.newSingle("sub-thread");
    	Mono<UserDetails> loginDetails = req.bodyToMono(UserDetails.class).doOnNext(userdetailsServiceImpl::saveUserdetails).subscribeOn(subWorker);
       	return ok().contentType(MediaType.APPLICATION_STREAM_JSON).build(loginDetails.then());
 	}
    

}
