package com.wentong.client;

import com.wentong.rpc.NameService;
import com.wentong.rpc.RpcAccessPoint;
import com.wentong.service.HelloService;

import java.net.URI;

public class Client {

    private static NameService nameService;
    private static RpcAccessPoint rpcAccessPoint;

    public static void main(String[] args) {
        URI uri = nameService.lookUpService("");
        HelloService remoteService = rpcAccessPoint.getRemoteService(uri, HelloService.class);
        remoteService.sayHello("xiaoming");

    }
}
