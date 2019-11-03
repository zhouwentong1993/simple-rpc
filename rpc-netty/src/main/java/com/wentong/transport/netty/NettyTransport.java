package com.wentong.transport.netty;

import com.wentong.transport.Transport;
import com.wentong.transport.command.Command;

import java.util.concurrent.CompletableFuture;

public class NettyTransport implements Transport {




    @Override
    public CompletableFuture<Command> run(Command request) {
        return null;
    }
}
