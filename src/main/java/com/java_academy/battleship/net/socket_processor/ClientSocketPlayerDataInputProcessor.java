package com.java_academy.battleship.net.socket_processor;

import com.java_academy.battleship.net.socket_processor.core.SocketProcessor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Siarhei Shauchenka on 15.07.2017.
 * <p>
 */
public class ClientSocketPlayerDataInputProcessor implements SocketProcessor {

    private Socket socket;

    @Override
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in))) {
            while (!Thread.interrupted()) {
                String inputData = inputStream.readLine();

                //TODO implement logic to pass data, for now it just send data to the server
                System.out.println("message  to server = " + inputData);
                dataOutputStream.writeUTF(inputData);
                dataOutputStream.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
