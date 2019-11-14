package com.wentong.transport.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求/响应头对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Header {
    private int requestId;
    private int version;
    private int type;


    // header 的大小
    public int length() {
        return 3 * Integer.BYTES;
    }
}
