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
public class ClientSocketServerDataInputProcessor implements SocketProcessor {

    private Socket socket;
    private SocketProcessorListener processorListener;

    @Override
    public void setSocket(final Socket socket) {
        this.socket = socket;
    }

    @Override
    public void setListener(final SocketProcessorListener processorListener) {
        this.processorListener = processorListener;
    }

    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {
            if (processorListener != null){
                processorListener.inProcess();
            }
            while (!Thread.currentThread().isInterrupted()) {
                String inputData = dataInputStream.readUTF();
                if (inputData.equals("bye")){
                    Thread.currentThread().interrupt();
                }
                //TODO implement logic to pass data, for now it just receive echo from the server
                System.out.println("message from server = " + inputData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
