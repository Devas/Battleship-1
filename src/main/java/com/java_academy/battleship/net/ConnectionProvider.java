package com.java_academy.battleship.net;

import com.java_academy.battleship.net.socket_providers.core.SocketProvider;

import java.io.Closeable;
import java.util.function.Supplier;

/**
 * Created by Siarhei Shauchenka on 14.07.17.
 * <p>
 * Provides a connection between ServerApplication and ClientApplication
 */

public class ConnectionProvider<T extends Closeable> {

    private SocketProvider<T> socketProvider;

    public ConnectionProvider(Supplier<SocketProvider<T>> socketProvider) {
        this.socketProvider = socketProvider.get();
    }

    SocketProvider<T> getSocketProvider() {
        return socketProvider;
    }
}
