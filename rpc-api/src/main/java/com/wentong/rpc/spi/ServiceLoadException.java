package com.wentong.rpc.spi;

public class ServiceLoadException extends RuntimeException {

    private String message;

    public ServiceLoadException(String message) {
        super(message);
    }

    public ServiceLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
