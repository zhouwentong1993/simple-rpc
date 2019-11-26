package com.wentong.transport;

import java.io.Closeable;
import java.io.IOException;
import java.net.SocketAddress;
import java.util.concurrent.TimeoutException;

public interface TransportClient extends Closeable {

    Transport createTransport(SocketAddress address, long connectionTimeout) throws TimeoutException, InterruptedException;

    @Override
    void close() throws IOException;
}
