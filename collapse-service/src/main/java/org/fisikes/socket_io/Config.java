package org.fisikes.socket_io;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.context.annotation.Bean;


@org.springframework.context.annotation.Configuration
public class Config {

    @Bean
    public SocketIOServer socketIOServer() {
        final Configuration config = new Configuration();
        config.setHostname("0.0.0.0");
        config.setPort(9092);
        config.setPingInterval(5000); // 心跳间隔为 5 秒
        config.setPingTimeout(10000); // 心跳超时时间为 10 秒

        final SocketIOServer socketIOServer = new SocketIOServer(config);
        final SocketIONamespace frontendNamespace = socketIOServer.addNamespace("/frontend");
        frontendNamespace.addListeners(frontendEventHandler());

        final SocketIONamespace backupNamespace = socketIOServer.addNamespace("/backup");
        backupNamespace.addListeners(backupEventHandler());

        socketIOServer.startAsync();
        return socketIOServer;
    }

    @Bean
    public FrontendEventHandler frontendEventHandler() {
        return new FrontendEventHandler();
    }

    @Bean
    public BackupEventHandler backupEventHandler() {
        return new BackupEventHandler();
    }


}
