package org.fisikes.socket_io;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class FrontendEventHandler {

    @OnConnect
    public void onConnect(SocketIOClient client) {
        System.out.println("Client connected: " + client.getSessionId());
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        System.out.println("Client disconnected: " + client.getSessionId());
    }

    @OnEvent("message")
    public void onMessage(SocketIOClient client, String data) {
        log.info("Message received: {}, session:{}", data, client.getSessionId());
        client.sendEvent("message", "frontendHandler Message received: " + data);
    }
}
