package com.wentong.transport.command;

import lombok.Data;

/**
 * 请求响应封装对象
 */
@Data
public class Command {
    private Header header;
    private byte[] payload;
}
