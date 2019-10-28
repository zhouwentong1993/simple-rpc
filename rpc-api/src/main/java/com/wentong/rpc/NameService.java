package com.wentong.rpc;

import java.net.URI;

public interface NameService {

    URI lookUpService(String serviceName);

    void registerService(String serviceName, URI uri);

}
