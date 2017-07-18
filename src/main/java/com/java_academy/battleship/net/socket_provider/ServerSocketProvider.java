package com.java_academy.battleship.net.socket_provider;

import com.java_academy.battleship.net.socket_processor.ServerSocketInputProcessor;
import com.java_academy.battleship.net.socket_processor.ServerSocketOutputProcessor;
import com.java_academy.battleship.net.socket_processor.ServerToClientConnectionProcessor;
import com.java_academy.battleship.net.socket_processor.core.SocketProcessorListener;
import com.java_academy.battleship.net.socket_provider.core.SocketProvider;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.function.Supplier;

/**
 * Created by Siarhei Shauchenka on 14.07.17.
 * <p>
 * Provides an access to {@link ServerSocket}
 */

public class ServerSocketProvider implements SocketProvider<ServerSocket> {

    private ServerSocket serverSocket;
    private Socket firstPlayerSocket;
    private Socket secondPlayerSocket;
    private ServerSocketOutputProcessor outputProcessor;
    private ExecutorService executorService = new ScheduledThreadPoolExecutor(3);

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
        outputProcessor.closeSocket();
        serverSocket.close();
    }

    @Override
    public void openSocketConnection(InetSocketAddress inetSocketAddress) throws IOException {
        if (!getSocket().isBound()) {
            int BACKLOG = 2;
            getSocket().bind(inetSocketAddress, BACKLOG);
        }
        executorService.execute(new ServerToClientConnectionProcessor(getSocket(), socket -> {
            firstPlayerSocket = socket;
            if (firstPlayerSocket != null && secondPlayerSocket != null){
                processConnection();
            }}));
        executorService.execute(new ServerToClientConnectionProcessor(getSocket(), socket -> {
            secondPlayerSocket = socket;
            if (firstPlayerSocket != null && secondPlayerSocket != null){
                processConnection();
            }}));
    }

    @Override
    public void processConnection() {
        ServerSocketInputProcessor firstPlayerInputProcessor = new ServerSocketInputProcessor();
        firstPlayerInputProcessor.setSocket(firstPlayerSocket);
        firstPlayerInputProcessor.setListener(message -> {
            System.out.println("message from first client = " + message);
            outputProcessor.sendMessage("echo message from first player = " + message);
        });
        executorService.execute(firstPlayerInputProcessor);

        ServerSocketInputProcessor secondPlayerInputProcessor = new ServerSocketInputProcessor();
        secondPlayerInputProcessor.setSocket(secondPlayerSocket);
        secondPlayerInputProcessor.setListener(message -> {
            System.out.println("message from second client = " + message);
            outputProcessor.sendMessage("echo message from second player = " + message);
        });
        executorService.execute(secondPlayerInputProcessor);

        outputProcessor = new ServerSocketOutputProcessor();
        outputProcessor.setSocket(firstPlayerSocket, secondPlayerSocket);

    }

    @Override
    public void sendMessage(String string) {
        outputProcessor.sendMessage(string);
    }
}
