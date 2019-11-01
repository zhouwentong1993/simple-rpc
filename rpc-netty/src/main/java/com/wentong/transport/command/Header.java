package com.wentong.transport.command;

import lombok.Data;

/**
 * 请求/响应头对象
 */
@Data
public class Header {
    private int uniqueId;
    private int version;
    private int type;


    // header 的大小
    public int length() {
        return 3 * Integer.BYTES;
    }
}
