package com.java_academy.battleship.net.socket_processor;

import com.java_academy.battleship.net.socket_processor.core.SocketProcessor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Siarhei Shauchenka on 15.07.2017.
 * <p>
 */
public class ClientSocketServerDataInputProcessor implements SocketProcessor {

    private Socket socket;

    @Override
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {
            while (!Thread.interrupted()) {
                String inputData = dataInputStream.readUTF();

                //TODO implement logic to pass data, for now it just receive echo from the server
                System.out.println("message from server = " + inputData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
