package com.java_academy.battleship.net;

import com.java_academy.battleship.net.socket_provider.ServerSocketProvider;
import com.java_academy.battleship.net.socket_provider.core.SocketProvider;
import org.testng.annotations.Test;

import java.net.ServerSocket;
import java.util.function.Supplier;

import static org.mockito.Mockito.mock;
import static org.testng.AssertJUnit.assertEquals;
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
        Supplier<SocketProvider<ServerSocket>> supplier = () -> new ServerSocketProvider(){
            @Override
            public ServerSocket getSocket() {
                return mock(ServerSocket.class);
            }
        };
        connectionProvider = new ConnectionProvider<>(supplier);
        assertNotNull(connectionProvider);
    }

    public void getServerSocketProviderTest(){
        SocketProvider<ServerSocket> serverSocketProvider = new ServerSocketProvider();
        assertNotNull(serverSocketProvider);
    }

    public void openConnectionTest(){
      // assertEquals(connectionProvider.openConnection(new InetSocketAddress("localhost", 3000)));
    }
}
