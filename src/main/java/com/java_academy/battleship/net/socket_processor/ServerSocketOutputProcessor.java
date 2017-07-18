package com.java_academy.battleship.net.socket_processor;

import com.java_academy.battleship.net.socket_processor.core.OutputSocketProcessor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static com.java_academy.battleship.net.ConnectionProvider.executorService;

/**
 * Created by Siarhei Shauchenka on 15.07.2017.
 * <p>
 * Implementation of Runnable interface for client connection,
 * It pass data to the Messages Observer
 */

public class ServerSocketOutputProcessor implements OutputSocketProcessor{

    private Socket firstPlayerSocket;
    private Socket secondPlayerSocket;
    private String message;
    private DataOutputStream firstPlayerOutput;
    private DataOutputStream secondPlayerOutput;

    @Override
    public void run() {
        try {
            firstPlayerOutput.writeUTF(message);
            firstPlayerOutput.flush();
            secondPlayerOutput.writeUTF(message);
            secondPlayerOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setSocket(Socket... socket) {
        firstPlayerSocket = socket[0];
        secondPlayerSocket = socket[1];
        try {
            firstPlayerOutput = new DataOutputStream(firstPlayerSocket.getOutputStream());
            secondPlayerOutput = new DataOutputStream(secondPlayerSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String message) {
        this.message = message;
        executorService.execute(this);
    }

    @Override
    public void closeSocket() {
        try {
            firstPlayerOutput.close();
            secondPlayerOutput.close();
            firstPlayerSocket.close();
            secondPlayerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
