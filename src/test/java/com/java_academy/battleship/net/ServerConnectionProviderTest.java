package com.java_academy.battleship.net;

import com.java_academy.battleship.net.socket_processor.ServerSocketProcessor;
import com.java_academy.battleship.net.socket_provider.ServerSocketProvider;
import com.java_academy.battleship.net.socket_provider.core.SocketProvider;
import org.testng.annotations.Test;

import java.net.InetSocketAddress;
import java.net.ServerSocket;

import static org.testng.AssertJUnit.assertNotNull;

/**
 * Created by Siarhei Shauchenka on 14.07.17.
 * <p>
 * Provides tests for {@link ConnectionProvider}
 */
@Test
public class ServerConnectionProviderTest {

    private ConnectionProvider<ServerSocket> connectionProvider;

    public void createProviderTest(){
        connectionProvider = new ConnectionProvider<>(ServerSocketProvider::new);
        assertNotNull(connectionProvider);
    }

    public void getServerSocketProviderTest(){
        SocketProvider<ServerSocket> serverSocketProvider = new ServerSocketProvider();
        assertNotNull(serverSocketProvider);
    }

    //TODO create mock for test
    public void openConnectionTest(){
       // connectionProvider.openConnection(ServerSocketProcessor::new, new InetSocketAddress("localhost", 3000));
    }
}
