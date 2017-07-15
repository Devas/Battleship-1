package com.java_academy.battleship.net.socket_processor;

import com.java_academy.battleship.net.socket_processor.core.SocketProcessor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Siarhei Shauchenka on 15.07.2017.
 * <p>
 * Implementation of Runnable interface for client connection,
 * It pass data to the Messages Observer
 */

public class ServerSocketProcessor implements SocketProcessor{

    private Socket client;

    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream())) {
            while (!Thread.interrupted()) {
                String inputData = dataInputStream.readUTF();

                //TODO implement logic to pass data, for now it just send echo to the client
                System.out.println("message from a client = " + inputData);
                dataOutputStream.writeUTF(inputData);
                dataOutputStream.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSocket(Socket socket) {
        this.client = socket;
    }
}
