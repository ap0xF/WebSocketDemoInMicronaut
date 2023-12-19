package com.wsd.controller;


import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;

import com.wsd.service.WebSocketService;
import jakarta.inject.Inject;

@ServerWebSocket("/ws/{string}")
public class WebSocketController {

    @Inject
    private WebSocketService webSocketService;

    @OnOpen
    public void onOpen(WebSocketSession session) {
        webSocketService.onOpen(session);
    }

    @OnMessage
    public String onMessage(String message, WebSocketSession session) {
        return webSocketService.onMessage(message, session);
    }

    @OnClose
    public void onClose(WebSocketSession session) {

    }
}
