package org.fisikes.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * @author yudong.li
 * @version 1.0
 */
@Slf4j
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
        final ByteBuffer in = (ByteBuffer) msg;
        log.info("Server received: {}", in.toString());
        ctx.write(in);
    }

    @Override
    public void channelReadComplete(final ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
        log.error("Exception caught", cause);
        ctx.close();
    }
}
