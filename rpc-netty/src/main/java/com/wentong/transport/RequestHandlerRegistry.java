package com.wentong.transport;

import com.wentong.rpc.spi.ServiceSupport;
import com.wentong.transport.netty.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求处理类注册器
 */
public class RequestHandlerRegistry {

    private RequestHandlerRegistry(){}

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestHandlerRegistry.class);

    private static Map<Integer, RequestHandler> requestHandlerMap = new HashMap<>();

    private static RequestHandlerRegistry instance = new RequestHandlerRegistry();

    static {
        Collection<RequestHandler> requestHandlers = ServiceSupport.loadAll(RequestHandler.class);
        for (RequestHandler requestHandler : requestHandlers) {
            requestHandlerMap.put(requestHandler.type(), requestHandler);
            LOGGER.info("请求处理器注册{}", requestHandler);
        }
        if (requestHandlerMap.size() == 0) {
            throw new IllegalStateException();
        }
    }

    public static RequestHandlerRegistry getInstance() {
        return instance;
    }

    public RequestHandler get(int type) {
        return requestHandlerMap.get(type);
    }
}
