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
        ConnectionProvider<Socket> connectionProvider = new ConnectionProvider<>(ClientSocketProvider::new);
        connectionProvider.openConnection(new InetSocketAddress("localhost", 3000));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        connectionProvider.sendMessage("dsfdsfsd");


    }
}
