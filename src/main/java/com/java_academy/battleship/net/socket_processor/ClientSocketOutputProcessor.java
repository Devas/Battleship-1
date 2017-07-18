package com.java_academy.battleship.net.socket_processor;

import com.java_academy.battleship.net.socket_processor.core.OutputSocketProcessor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.Socket;

import static com.java_academy.battleship.net.ConnectionProvider.executorService;

/**
 * Created by Siarhei Shauchenka on 15.07.2017.
 * <p>
 *  Implements logic for transferring data which was entered by a Player
 */
public class ClientSocketOutputProcessor implements OutputSocketProcessor {

    private Socket socket;
    private String message;
    private DataOutputStream dataOutputStream;


    @Override
    public void setSocket(Socket... socket) {
        this.socket = socket[0];
        try {
            dataOutputStream =  new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(final String message) {
        this.message = message;
        executorService.execute(this);
    }

    @Override
    public void closeSocket() {
        try {
            socket.close();
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try (BufferedReader inputStream = new BufferedReader(new StringReader(message))) {
            String inputData = inputStream.readLine();
            dataOutputStream.writeUTF(inputData);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
