package org.fisikes.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.AllArgsConstructor;

/**
 * @author yudong.li
 * @version 1.0
 */
@AllArgsConstructor
public class EchoClient {

    private final String host;
    private final int port;

    public static void main(String[] args) {

        final EventLoopGroup group = new NioEventLoopGroup();





    }
}
