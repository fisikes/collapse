package org.fisikes.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.net.InetSocketAddress;

/**
 * @author yudong.li
 * @version 1.0
 */
@AllArgsConstructor
public class EchoClient {

    private final String host;
    private final int port;

    @SneakyThrows
    public void start() {
        final NioEventLoopGroup group = new NioEventLoopGroup();


        final Bootstrap bootstrap = new Bootstrap();

        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }

    }

    public static void main(String[] args) {
        new EchoClient("localhost", 19000).start();

    }
}
