package org.fisikes.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.net.InetSocketAddress;

/**
 * @author yudong.li
 * @version 1.0
 */
@AllArgsConstructor
public class EchoServer {

    private final int port;

    @SneakyThrows
    public static void main(String[] args) {
        int port = 8080;
        new EchoServer(port).start();
    }

    private void start() throws Exception {

        final EchoServerHandler echoServerHandler = new EchoServerHandler();

        final NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            final ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(final SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(echoServerHandler);
                        }
                    });

            final ChannelFuture future = bootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
