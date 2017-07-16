package com.java_academy.battleship.net.socket_provider.core;

import com.java_academy.battleship.net.socket_processor.core.SocketProcessor;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.function.Supplier;

/**
 * Created by Siarhei Shauchenka on 14.07.17.
 * <p>
 * Base interface for a SocketProvider class
 */
public interface SocketProvider<T extends Closeable> {
    T getSocket();
    void close() throws IOException;
    Socket openSocketConnection(InetSocketAddress inetSocketAddress) throws IOException;
    boolean processConnection(Supplier<SocketProcessor> supplier, InetSocketAddress inetSocketAddress);
}
