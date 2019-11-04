package com.wentong.transport;

import com.wentong.transport.command.Command;

import java.util.concurrent.CompletableFuture;

/**
 * 响应的抽象，通过全局的 requestId 关联
 */
public class ResponseFuture {
    private Integer requestId;
    private CompletableFuture<Command> completableFuture;
    private Long timestamp;

    public ResponseFuture(Integer requestId, CompletableFuture<Command> completableFuture) {
        this.requestId = requestId;
        this.completableFuture = completableFuture;
        this.timestamp = System.nanoTime();
    }

    public Integer getRequestId() {
        return requestId;
    }

    public CompletableFuture<Command> getCompletableFuture() {
        return completableFuture;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
