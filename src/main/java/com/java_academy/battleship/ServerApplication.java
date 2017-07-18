package com.java_academy.battleship;


import com.java_academy.battleship.net.ConnectionProvider;
import com.java_academy.battleship.net.socket_provider.ServerSocketProvider;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * Created by Siarhei Shauchenka on 14.07.17.
 * <p>
 * Enter point for the Server part
 */
public class ServerApplication {

    public static void main(String[] args) throws IOException {
        ServerApplication serverApplication=new ServerApplication();
        serverApplication.start();
    }

    private void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        ServerSocketProvider serverSocketProvider = new ServerSocketProvider(serverSocket);
        ConnectionProvider<ServerSocket> connectionProvider = new ConnectionProvider<>(serverSocketProvider);
        connectionProvider.openConnection(new InetSocketAddress("localhost", 3000));
    }
}
