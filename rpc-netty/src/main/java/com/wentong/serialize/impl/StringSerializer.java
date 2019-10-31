package com.wentong.serialize.impl;

import com.wentong.serialize.Serializer;
import com.wentong.serialize.exception.SerializeException;

import java.io.UnsupportedEncodingException;

import static com.wentong.serialize.SerializerType.STRING_TYPE;

public class StringSerializer implements Serializer<String> {

    @Override
    public byte getType() {
        return STRING_TYPE;
    }

    @Override
    public String parse(byte[] data, int offset, int length) {
        try {
            return new String(data, offset, length, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new SerializeException(e);
        }
    }

    @Override
    public void serialize(String data, byte[] source, int offset, int length) {
        try {
            byte[] bytes = data.getBytes("UTF-8");
            System.arraycopy(bytes, 0, source, offset, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new SerializeException(e);
        }

    }

    @Override
    public Class<String> getSerializedClass() {
        return String.class;
    }

}
