package com.java_academy.battleship;

import com.java_academy.battleship.net.ConnectionProvider;
import com.java_academy.battleship.net.socket_provider.ClientSocketProvider;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Siarhei Shauchenka on 15.07.2017.
 * <p>
 */
public class ClientApp  {

    public static void main(String[] args) {
        ClientApp clientApp = new ClientApp();
        clientApp.start();
    }

    private void start() {
        ClientSocketProvider clientSocketProvider = new ClientSocketProvider(Socket::new);
        ConnectionProvider<Socket> connectionProvider = new ConnectionProvider<>(clientSocketProvider);
        connectionProvider.openConnection(new InetSocketAddress("localhost", 3000));

        //Fake for tests
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        connectionProvider.sendMessage("dsfdsfsd");
    }
}
