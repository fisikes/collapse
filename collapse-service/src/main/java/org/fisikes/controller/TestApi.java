package org.fisikes.controller;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class TestApi {

    private final SocketIOServer socketIOServer;

    @GetMapping("test")
    public String test() {
        return "test";
    }

    @GetMapping("emit")
    public String emit(final String namespace, final String message) {

        log.info("namespace: {}, emit: {}", namespace, message);
        final SocketIONamespace namespaceObj = socketIOServer.getNamespace("/" + namespace);

        if (namespaceObj == null) {
            return "no namespace found";
        }

        namespaceObj.getBroadcastOperations().sendEvent("message", message);
        return "ok";
    }
}
