package com.wentong.transport.command;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 请求响应封装对象
 */
@Data
@AllArgsConstructor
public class Command {
    private Header header;
    private byte[] payload;
}
