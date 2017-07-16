package com.java_academy.battleship.net;

import com.java_academy.battleship.net.socket_processor.core.SocketProcessor;
import com.java_academy.battleship.net.socket_provider.core.SocketProvider;

import java.io.Closeable;
import java.net.InetSocketAddress;
import java.util.function.Supplier;

/**
 * Created by Siarhei Shauchenka on 14.07.17.
 * <p>
 * Provides a connection between ServerApplication and ClientApplication.
 * <p>
 * T for a server should be {@link java.net.ServerSocket} for a client - {@link java.net.Socket}
 */

public class ConnectionProvider<T extends Closeable> {

    private SocketProvider<T> socketProvider;

    public ConnectionProvider(Supplier<SocketProvider<T>> socketProvider) {
        this.socketProvider = socketProvider.get();
    }

    public boolean openConnection(Supplier<SocketProcessor> supplier, InetSocketAddress inetSocketAddress) {
        return socketProvider.processConnection(supplier, inetSocketAddress);
    }

}
