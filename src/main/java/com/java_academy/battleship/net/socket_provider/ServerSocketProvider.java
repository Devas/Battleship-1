package com.java_academy.battleship.net.socket_provider;

import com.java_academy.battleship.net.socket_processor.core.SocketProcessor;
import com.java_academy.battleship.net.socket_provider.core.SocketProvider;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Supplier;

/**
 * Created by Siarhei Shauchenka on 14.07.17.
 * <p>
 * Provides an access to {@link ServerSocket}
 */

public class ServerSocketProvider implements SocketProvider<ServerSocket> {

    private ServerSocket serverSocket;

    public ServerSocketProvider() {
        try {
            serverSocket = new ServerSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ServerSocket getSocket() {
        return serverSocket;
    }

    @Override
    public void close() throws IOException {
        serverSocket.close();
    }

    @Override
    public Socket openSocketConnection(InetSocketAddress inetSocketAddress) throws IOException {
        if (!serverSocket.isBound()) {
            int BACKLOG = 2;
            serverSocket.bind(inetSocketAddress, BACKLOG);
        }
        return serverSocket.accept();
    }

    @Override
    public void processConnection(Supplier<SocketProcessor> supplier, InetSocketAddress inetSocketAddress) {
        int numberOfConnections = 0;
        int MAX_NUMBER_OF_CONNECTIONS = 2;
        while (numberOfConnections < MAX_NUMBER_OF_CONNECTIONS) {
            try {
                System.out.println("Whaiting for a client...");
                Socket socket = openSocketConnection(inetSocketAddress);
                System.out.println("Client connected");
                numberOfConnections++;
                SocketProcessor processor = supplier.get();
                processor.setSocket(socket);
                new Thread(processor).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //TODO implement callback that connections was created
    }

}
