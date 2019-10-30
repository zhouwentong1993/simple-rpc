package com.wentong.rpc.spi;

import java.util.*;

public class ServiceSupport {

    private ServiceSupport() {
    }

    public static synchronized <S> S load(Class<S> service) {
        ServiceLoader<S> loader = ServiceLoader.load(service);
        Iterator<S> iterator = loader.iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            throw new ServiceLoadException("对应的服务:" + service.getName() + " 无法被加载");
        }
    }

    public static synchronized <S> Collection<S> loadAll(Class<S> service) {
        ServiceLoader<S> loader = ServiceLoader.load(service);
        List<S> list = new ArrayList<>();
        loader.iterator().forEachRemaining(list::add);
        return list;
    }

}
