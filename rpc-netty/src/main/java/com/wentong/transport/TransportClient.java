package com.wentong.transport;

import java.io.Closeable;
import java.io.IOException;
import java.net.SocketAddress;

public interface TransportClient extends Closeable {

    Transport createTransport(SocketAddress address, long connectionTimeout);

    @Override
    void close() throws IOException;
}
