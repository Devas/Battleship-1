package com.java_academy.battleship;


import com.java_academy.battleship.net.ConnectionProvider;
import com.java_academy.battleship.net.socket_processor.ServerSocketProcessor;
import com.java_academy.battleship.net.socket_provider.ServerSocketProvider;

import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * Created by Siarhei Shauchenka on 14.07.17.
 * <p>
 * Enter point for Server part
 */
public class ServerApplication
{
    public static void main( String[] args )
    {
        ConnectionProvider<ServerSocket> connectionProvider = new ConnectionProvider<>(ServerSocketProvider::new);
        connectionProvider.openConnection(ServerSocketProcessor::new, new InetSocketAddress("localhost", 3000));
    }
}
