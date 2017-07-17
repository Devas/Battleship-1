package com.java_academy.battleship.net.socket_processor;

import com.java_academy.battleship.net.socket_processor.core.SocketProcessor;
import com.java_academy.battleship.net.socket_processor.core.SocketProcessorListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.net.Socket;

/**
 * Created by Siarhei Shauchenka on 15.07.2017.
 * <p>
 * Implementation of Runnable interface for client connection,
 * It pass data to the Messages Observer
 */

public class ServerSocketProcessor implements SocketProcessor{

    private Socket client;
    private SocketProcessorListener processorListener;

    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream())) {
            if (processorListener != null){
                processorListener.inProcess();
            }
            while (!Thread.currentThread().isInterrupted()) {
                String inputData = dataInputStream.readUTF();
                if (inputData.equals("bye")){
                    Thread.currentThread().interrupt();
                }
                //TODO implement logic to pass data, for now it just send echo to the client
                System.out.println("message from a client = " + inputData);
                dataOutputStream.writeUTF(inputData);
                dataOutputStream.flush();
            }

        } catch (IOException e) {
            String message = "";
            if (e.getLocalizedMessage() != null){
                message = e.getLocalizedMessage();
            }
            processorListener.processFailed(message);
        }
    }

    @Override
    public void setSocket(Socket socket) {
        this.client = socket;
    }

    @Override
    public void setListener(final SocketProcessorListener processorListener) {
        this.processorListener = processorListener;
    }

    @Override
    public void setOutputPipe(PipedOutputStream outputPipe) {

    }
}
