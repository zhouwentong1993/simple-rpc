package com.wentong.transport.netty;

import com.wentong.transport.InFlightRequests;
import com.wentong.transport.ResponseFuture;
import com.wentong.transport.command.Command;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 响应的处理，这个是针对 Netty client 来实现的。
 * 但是为什么没有 Request呢，不应该是一个请求发生，然后 inFlightRequests 开始初始化吗？
 */
@ChannelHandler.Sharable
public class ResponseInvocation extends SimpleChannelInboundHandler<Command> {

    private static final Logger logger = LoggerFactory.getLogger(ResponseInvocation.class);
    private InFlightRequests inFlightRequests;

    public ResponseInvocation(InFlightRequests inFlightRequests) {
        this.inFlightRequests = inFlightRequests;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Command command) throws Exception {
        ResponseFuture responseFuture = inFlightRequests.remove(command.getHeader().getRequestId());
        if (responseFuture != null) {
            // 如果完成了该请求，标注该 CompleteFuture 完成，因为有可能超时
            responseFuture.getCompletableFuture().complete(command);
        } else {
            logger.warn("Drop response:{}", command);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("Exception: ", cause);
        super.exceptionCaught(ctx, cause);
        Channel channel = ctx.channel();
        if(channel.isActive()) {
            ctx.close();
        }
    }
}
