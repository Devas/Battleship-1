package com.javaAcademy.Battleship.net;

import com.javaAcademy.Battleship.net.socket_providers.BSServerSocketProvider;
import com.javaAcademy.Battleship.net.socket_providers.core.SocketProvider;
import org.testng.annotations.Test;

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
        connectionProvider = new ConnectionProvider<>(BSServerSocketProvider::new);
        assertNotNull(connectionProvider);
    }

    public void getServerSocketProviderTest(){
        SocketProvider<ServerSocket> serverSocketProvider = connectionProvider.getSocketProvider();
        assertNotNull(serverSocketProvider);
    }

}
