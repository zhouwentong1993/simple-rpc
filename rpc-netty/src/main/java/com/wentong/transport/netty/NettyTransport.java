package com.wentong.transport.netty;

import com.wentong.transport.InFlightRequests;
import com.wentong.transport.ResponseFuture;
import com.wentong.transport.Transport;
import com.wentong.transport.command.Command;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;

import java.util.concurrent.CompletableFuture;

public class NettyTransport implements Transport {

    private Channel channel;
    private InFlightRequests inFlightRequests;

    public NettyTransport(Channel channel, InFlightRequests inFlightRequests) {
        this.channel = channel;
        this.inFlightRequests = inFlightRequests;
    }

    @Override
    public CompletableFuture<Command> run(Command request) {

        CompletableFuture<Command> completableFuture = new CompletableFuture<>();
        try {
            inFlightRequests.put(new ResponseFuture(request.getHeader().getRequestId(), completableFuture));
            // 将请求发送出去，就等着
            channel.writeAndFlush(request).addListener((ChannelFutureListener) channelFuture -> {
                // 如果没有执行成功，则异常结束
                if (!channelFuture.isSuccess()) {
                    completableFuture.completeExceptionally(channelFuture.cause());
                    channel.close();
                }
            });
        } catch (Throwable throwable) {
            inFlightRequests.remove(request.getHeader().getRequestId());
            completableFuture.completeExceptionally(throwable);
        }
        return completableFuture;
    }
}
