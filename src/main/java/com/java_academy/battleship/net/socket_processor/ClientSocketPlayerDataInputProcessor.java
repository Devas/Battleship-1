package com.java_academy.battleship.net.socket_processor;

import com.java_academy.battleship.net.socket_processor.core.SocketProcessor;
import com.java_academy.battleship.net.socket_processor.core.SocketProcessorListener;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Siarhei Shauchenka on 15.07.2017.
 * <p>
 *  Implements logic for transferring data which was entered by a Player
 */
public class ClientSocketPlayerDataInputProcessor implements SocketProcessor {

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
        try (DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in))) {
            if (processorListener != null){
                processorListener.inProcess();
            }
            while (!Thread.currentThread().isInterrupted()) {
                String inputData = inputStream.readLine();
                if (inputData.equals("bye")){
                    Thread.currentThread().interrupt();
                }
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
