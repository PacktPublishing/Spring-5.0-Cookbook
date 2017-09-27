package org.packt.messaging.core.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

@Component
public class HotlineSocketHandler extends TextWebSocketHandler {
	
	List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {
		
		for(WebSocketSession webSocketSession : sessions) {
			Map value = new Gson().fromJson(message.getPayload(), Map.class);
			String[] data = value.get("data").toString().split(",");
			// save data[2] as complaints inside the database
			webSocketSession.sendMessage(new TextMessage("Dear " + data[0] + ", you complaint is now being processed..."));
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			sessions.add(session);
	}
}
