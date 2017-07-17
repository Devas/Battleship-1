package com.java_academy.battleship.net.socket_provider;

import com.java_academy.battleship.net.socket_processor.core.SocketProcessor;
import com.java_academy.battleship.net.socket_processor.core.SocketProcessorListener;
import com.java_academy.battleship.net.socket_provider.core.SocketProvider;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
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
        getSocket().close();
    }

    @Override
    public Socket openSocketConnection(InetSocketAddress inetSocketAddress) throws IOException {
        if (!getSocket().isBound()) {
            int BACKLOG = 2;
            getSocket().bind(inetSocketAddress, BACKLOG);
        }
        return getSocket().accept();
    }

    @Override
    public boolean processConnection(Supplier<SocketProcessor> supplier, InetSocketAddress inetSocketAddress) {
        int numberOfConnections = 0;
        int MAX_NUMBER_OF_CONNECTIONS = 2;
        while (numberOfConnections < MAX_NUMBER_OF_CONNECTIONS) {
            try {
                System.out.println("Waiting for a client...");
                Socket socket = openSocketConnection(inetSocketAddress);
                System.out.println("Client connected");
                SocketProcessor processor = supplier.get();
                processor.setSocket(socket);
                processor.setListener(new SocketProcessorListener() {
                    @Override
                    public void inProcess() {
                        System.out.println("Connection is under process!");
                    }

                    @Override
                    public void processFailed(String message) {
                        System.out.println("Process failed in case of: " + message);
                    }
                });
                new Thread(processor).start();
                numberOfConnections++;
            }catch (SocketException socketException){
                System.out.println("Connection failed! " + socketException.getLocalizedMessage());
                //TODO implements here java.net.SocketException: Unresolved address when address is not correct
                return false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public void sendMessage(String message) {

    }
}
