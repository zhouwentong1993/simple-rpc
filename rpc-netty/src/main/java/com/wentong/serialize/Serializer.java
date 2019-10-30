package com.wentong.serialize;

public interface Serializer<T> {

    byte getType();

    T parse(byte[] data, int offset, int length);

    void serialize(T data, byte[] source, int offset, int length);

    Class<T> getSerializedClass();
}
