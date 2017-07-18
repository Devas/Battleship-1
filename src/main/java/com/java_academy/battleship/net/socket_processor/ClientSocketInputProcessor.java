package com.java_academy.battleship.net.socket_processor;

import com.java_academy.battleship.net.socket_processor.core.SocketProcessor;
import com.java_academy.battleship.net.socket_processor.core.SocketProcessorListener;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Siarhei Shauchenka on 15.07.2017.
 * <p>
 * Implements logic for transferring data which was received from the Server
 */
public class ClientSocketInputProcessor implements SocketProcessor {

    private Socket socket;
    private SocketProcessorListener processorListener;

    @Override
    public void setSocket(Socket... socket) {
        this.socket = socket[0];
    }

    @Override
    public void setListener(final SocketProcessorListener processorListener) {
        this.processorListener = processorListener;
    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void closeSocket() {

    }

    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {
            while (!Thread.currentThread().isInterrupted()) {
                String inputData = dataInputStream.readUTF();
                if (inputData.equals("bye")){
                    Thread.currentThread().interrupt();
                }
                if (processorListener != null){
                    processorListener.messageReceived(inputData);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
