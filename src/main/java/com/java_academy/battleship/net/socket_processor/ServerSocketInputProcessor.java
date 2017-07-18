package com.java_academy.battleship.net.socket_processor;

import com.java_academy.battleship.net.socket_processor.core.InputSocketProcessor;
import com.java_academy.battleship.net.socket_processor.core.SocketProcessor;
import com.java_academy.battleship.net.socket_processor.core.SocketProcessorListener;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Siarhei Shauchenka on 18.07.17.
 */
public class ServerSocketInputProcessor implements InputSocketProcessor {

    private Socket socket;
    private SocketProcessorListener processorListener;

    @Override
    public void setSocket(Socket... socket) {
        this.socket = socket[0];
    }

    @Override
    public void setListener(SocketProcessorListener processorListener) {
        this.processorListener = processorListener;
    }


    @Override
    public void closeSocket() {

    }

    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())){
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
