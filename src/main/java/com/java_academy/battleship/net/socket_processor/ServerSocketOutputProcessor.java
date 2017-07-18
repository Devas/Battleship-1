package com.java_academy.battleship.net.socket_processor;

import com.java_academy.battleship.net.socket_processor.core.SocketProcessor;
import com.java_academy.battleship.net.socket_processor.core.SocketProcessorListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by Siarhei Shauchenka on 15.07.2017.
 * <p>
 * Implementation of Runnable interface for client connection,
 * It pass data to the Messages Observer
 */

public class ServerSocketOutputProcessor implements SocketProcessor{

    private Socket firstPlayerSocket;
    private Socket secondPlayerSocket;
    private String message;
    private DataOutputStream firstPlayerOutput;
    private DataOutputStream secondPlayerOutput;
    private ExecutorService executorService = new ScheduledThreadPoolExecutor(3);

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
    public void setListener(final SocketProcessorListener processorListener) {

    }

    @Override
    public void sendMessage(String message) {
        this.message = message;
        executorService.execute(this);
    }

    @Override
    public void closeSocket() {
        try {
            firstPlayerSocket.close();
            secondPlayerSocket.close();
            firstPlayerOutput.close();
            secondPlayerOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
