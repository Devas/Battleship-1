package com.java_academy.battleship.net.socket_processor;

import com.java_academy.battleship.net.socket_processor.core.SocketConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Siarhei Shauchenka on 18.07.17.
 */
public class ServerToClientConnectionProcessor implements Runnable {

    private SocketConnectionListener connectionListener;
    private ServerSocket serverSocket;

    public ServerToClientConnectionProcessor(ServerSocket serverSocket, SocketConnectionListener connectionListener) {
        this.serverSocket = serverSocket;
        this.connectionListener = connectionListener;
    }

    @Override
    public void run() {
        try {
            Socket socket = serverSocket.accept();
            if (connectionListener != null){
                connectionListener.socketConnected(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
