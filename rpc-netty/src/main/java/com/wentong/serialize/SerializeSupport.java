package com.wentong.serialize;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class SerializeSupport {

    private Map<Byte, Class> byteClassMap = new HashMap<>();
    private Map<Class<?>, Serializer> classSerializerMap = new HashMap<>();

    static {
        for (Serializer serializer : ServiceLoader.load(Serializer.class)) {

        }
    }

    private static void registerType(byte type, Class clazz, Serializer serializer) {

    }

}
