package com.wentong.transport.netty;

import com.wentong.transport.command.Command;

/**
 * 请求处理器
 */
public interface RequestHandler {

    /**
     * 处理请求，请求和响应都是通过 Command 封装的。
     */
    Command handleRequest(Command requestCommand);

    /**
     * 该请求的处理类型
     */
    int type();
}
