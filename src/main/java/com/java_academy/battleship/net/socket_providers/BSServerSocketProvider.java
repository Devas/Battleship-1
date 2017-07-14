package com.java_academy.battleship.net.socket_providers;

import com.java_academy.battleship.net.socket_providers.core.SocketProvider;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * Created by Siarhei Shauchenka on 14.07.17.
 * <p>
 * Provides an access to {@link ServerSocket}
 */

public class BSServerSocketProvider implements SocketProvider<ServerSocket> {

    private ServerSocket serverSocket;

    @Override
    public void createSocket(String ipAddress, int port) throws IOException {
        int BACKLOG = 2;
        serverSocket = new ServerSocket(port, BACKLOG, InetAddress.getByName(ipAddress));
    }

    @Override
    public ServerSocket getSocket() {
        return serverSocket;
    }

    @Override
    public void close() throws IOException {
        serverSocket.close();
    }
}
