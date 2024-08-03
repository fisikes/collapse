package org.fisikes.socket_io;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class BackupEventHandler {

    @OnConnect
    public void onConnect(SocketIOClient client) {
        log.info("onConnect, client: {}", client.getRemoteAddress());
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        System.out.println("Client disconnected: " + client.getSessionId());
    }

    @OnEvent("message")
    public void onMessage(SocketIOClient client, String data) {
        System.out.println("Received message: " + data);
        client.sendEvent("message", "Message received: " + data);
    }
}
