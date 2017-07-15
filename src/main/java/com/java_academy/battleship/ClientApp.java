package com.java_academy.battleship;

import com.java_academy.battleship.net.ConnectionProvider;
import com.java_academy.battleship.net.socket_processor.ClientSocketPlayerDataInputProcessor;
import com.java_academy.battleship.net.socket_processor.ClientSocketServerDataInputProcessor;
import com.java_academy.battleship.net.socket_provider.ClientSocketProvider;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Siarhei Shauchenka on 15.07.2017.
 * <p>
 */
public class ClientApp  {

    public static void main(String[] args) {
        ConnectionProvider<Socket> connectionProvider = new ConnectionProvider<>(ClientSocketProvider::new);
        //Open connection for PlayerInput
        connectionProvider.openConnection(ClientSocketPlayerDataInputProcessor::new, new InetSocketAddress("localhost", 3000));
        //Open connection for ServerInput
        connectionProvider.openConnection(ClientSocketServerDataInputProcessor::new, new InetSocketAddress("localhost", 3000));
    }
}
