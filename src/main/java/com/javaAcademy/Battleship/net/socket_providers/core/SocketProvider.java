package com.javaAcademy.Battleship.net.socket_providers.core;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Siarhei Shauchenka on 14.07.17.
 * <p>
 * Base interface for a SocketProvider class
 */
public interface SocketProvider<T extends Closeable> {
    void createSocket(String ipAddress, int port) throws IOException;
    T getSocket();
    void close() throws IOException;
}
