package com.wentong.transport.netty;

import com.wentong.transport.RequestHandlerRegistry;
import com.wentong.transport.command.Command;
import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 请求被调用时，server 处理
 */
@ChannelHandler.Sharable
public class RequestInvocation extends SimpleChannelInboundHandler<Command> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInvocation.class);

    private RequestHandlerRegistry requestHandlerRegistry;

    public RequestInvocation(RequestHandlerRegistry requestHandlerRegistry) {
        this.requestHandlerRegistry = requestHandlerRegistry;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, Command command) {
        RequestHandler requestHandler = requestHandlerRegistry.get(command.getHeader().getType());
        if (requestHandler != null) {
            Command response = requestHandler.handleRequest(command);
            if (response != null) {
                context.writeAndFlush(response).addListener((ChannelFutureListener) channelFuture -> {
                    if (!channelFuture.isSuccess()) {
                        LOGGER.warn("Write response failed!", channelFuture.cause());
                        context.channel().close();
                    }
                });
            } else {
                LOGGER.warn("Response is null!");
            }
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.warn("exception:{}", cause);
        super.exceptionCaught(ctx, cause);
        Channel channel = ctx.channel();
        if (channel.isActive()) {
            ctx.close();
        }
    }
}
