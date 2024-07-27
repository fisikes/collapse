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
        ctx.writeAndFlush(Unpooled.copiedBuffer("宝玉，我问你：至贵者是‘宝’，至坚者是‘玉’。尔有何贵？尔有何坚？", CharsetUtil.UTF_8));
    }

    // this method is called whenever data is received
    @Override
    protected void channelRead0(final ChannelHandlerContext channelHandlerContext, final ByteBuf byteBuf) throws Exception {

        int bytesLen = byteBuf.readableBytes();
        log.info("Received {} bytes", bytesLen);

        log.info("client received: {}", byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        log.error("exception caught", cause);
        ctx.close();
    }
}
