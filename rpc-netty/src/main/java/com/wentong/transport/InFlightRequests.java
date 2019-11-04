package com.wentong.transport;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.Closeable;
import java.util.Map;
import java.util.concurrent.*;

public class InFlightRequests implements Closeable {

    private static final int TIMEOUT = 10;
    // 信号量，用来控制请求数量的
    private Semaphore semaphore = new Semaphore(10);
    // 具体存放所有没有结束的请求
    private Map<Integer, ResponseFuture> requestMap = new ConcurrentHashMap<>();
    // 用来定时清理过期未删除的数据
    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder().setNameFormat("清理正在进行中的请求-%d").build());
    private ScheduledFuture scheduledFuture;


    public InFlightRequests() {
        scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(this::releaseTimeoutRequests, TIMEOUT, TIMEOUT, TimeUnit.SECONDS);
    }

    public void put(ResponseFuture responseFuture) throws InterruptedException, TimeoutException {
        if (semaphore.tryAcquire(TIMEOUT, TimeUnit.SECONDS)) {
            requestMap.put(responseFuture.getRequestId(), responseFuture);
        } else {
            throw new TimeoutException();
        }
    }

    // 移除一个，就要释放一个
    public ResponseFuture remove(Integer requestId) {
        ResponseFuture responseFuture = requestMap.remove(requestId);
        if (responseFuture != null) {
            semaphore.release();
        }
        return responseFuture;
    }

    public void releaseTimeoutRequests() {
        requestMap.entrySet().removeIf(entry -> {
            if (System.nanoTime() - entry.getValue().getTimestamp() > TIMEOUT * 1000000000L) {
                semaphore.release();
                return true;
            } else {
                return false;
            }
        });
    }


    @Override
    public void close() {
        scheduledFuture.cancel(true);
        scheduledExecutorService.shutdown();
    }
}
