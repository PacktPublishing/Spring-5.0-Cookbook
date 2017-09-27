package org.packt.messaging.core.config;

import java.time.Duration;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Mono;

@Component
public class MessageWebSocketHandler implements WebSocketHandler {
   
    @Override
    public Mono<Void> handle(WebSocketSession session) {
    	 return session.send(session.receive().map(str -> str.getPayloadAsText())
    			 .map(str -> "Howdy, " + str + "? Welcome to the Portal!" )
    			 .map(session::textMessage))
    			 .delayElement(Duration.ofMillis(2)).log();           
    }
}
