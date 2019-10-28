package com.wentong.rpc;

import java.io.Closeable;
import java.net.URI;

public interface RpcAccessPoint extends Closeable {

    /**
     * 获取服务提供者对象，通过 uri + 对象 Class 映射
     */
    <T> T getRemoteService(URI uri, Class<T> clazz);

    /**
     * 服务提供者注册接口，与 #getRemoteService 正好相似
     */
    <T> URI addServiceProvider(T service, Class<T> serviceClass);

    Closeable stopServer();

}
