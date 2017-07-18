package com.java_academy.battleship.net;

import com.java_academy.battleship.net.socket_provider.core.SocketProvider;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Created by Siarhei Shauchenka on 14.07.17.
 * <p>
 * Provides a connection between ServerApplication and ClientApplication.
 * <p>
 * T for a server should be {@link java.net.ServerSocket} for a client - {@link java.net.Socket}
 */

public class ConnectionProvider<T extends Closeable> {

    public static ExecutorService executorService = new ScheduledThreadPoolExecutor(3);

    public static void terminateExecutor(){
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(15, TimeUnit.SECONDS)){
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private SocketProvider<T> socketProvider;

    public ConnectionProvider(SocketProvider<T> socketProvider) {
        this.socketProvider = socketProvider;
    }

    public void openConnection(InetSocketAddress inetSocketAddress) {
        socketProvider.openSocketConnection(inetSocketAddress);
    }

    public void sendMessage(String string){
        socketProvider.sendMessage(string);
    }
}
