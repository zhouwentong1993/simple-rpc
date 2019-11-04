package com.wentong.transport;

import com.wentong.transport.command.Command;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

/**
 * 通信类，包含请求/响应，异步。
 */
public interface Transport {

    CompletableFuture<Command> run(Command request);

}
