package org.fisikes.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yudong.li
 * @version 1.0
 */
@ChannelHandler.Sharable
@Slf4j
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    @Override
    protected void channelRead0(final ChannelHandlerContext channelHandlerContext, final ByteBuf byteBuf) throws Exception {
        log.info("client received: {}", byteBuf.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        log.error("exception caught", cause);
        ctx.close();
    }
}
