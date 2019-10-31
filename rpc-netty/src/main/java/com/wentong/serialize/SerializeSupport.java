package com.wentong.serialize;

import com.wentong.rpc.spi.ServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class SerializeSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerializeSupport.class);

    private static Map<Byte, Class> byteClassMap = new HashMap<>();
    private static Map<Class<?>, Serializer> classSerializerMap = new HashMap<>();

    static {
        for (Serializer serializer : ServiceSupport.loadAll(Serializer.class)) {
            registerType(serializer.getType(), serializer.getSerializedClass(), serializer);
            LOGGER.info("注册编解码器：{} 成功", serializer);
        }
    }

    private static void registerType(byte type, Class clazz, Serializer serializer) {
        byteClassMap.put(type, clazz);
        classSerializerMap.put(clazz, serializer);
    }

    @Override
    public String toString() {
        return byteClassMap.toString() + "-----" + classSerializerMap.toString();
    }
}
