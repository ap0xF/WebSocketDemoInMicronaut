package com.wsd.service;

import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import jakarta.inject.Singleton;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


@Singleton
public class WebSocketService {

    private Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
    @OnOpen
    public void onOpen(WebSocketSession session) {
        sessions.add(session);
        System.out.println("WebSocketService.onOpen");
    }

    @OnMessage
    public String onMessage(String message, WebSocketSession session) {
        try{
            sessions.stream().filter(WebSocketSession::isOpen).forEach(s -> {
                s.sendSync("User said: " + message);
            });
            return "OnMessage function completed successfully";
        } catch (Exception e){
            return e.toString();
        }
    }

    @OnClose
    public void onClose(WebSocketSession session) {
        sessions.remove(session);
        System.out.println("WebSocketService.onClose");
    }
}
