package com.javaAcademy.Battleship.net.socket_providers;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.UnknownHostException;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * Created by Siarhei Shauchenka on 14.07.17.
 *
 * Provides tests for {@link BSServerSocketProvider}
 */
@Test
public class BSServerSocketProviderTest {

    private final int PORT = 8080;

    public void createServerSocketTest(){
        BSServerSocketProvider serverSocketProvider = new BSServerSocketProvider();
        try {
            String CORRECT_IP = "localhost";
            serverSocketProvider.createSocket(CORRECT_IP, PORT);
            serverSocketProvider.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(serverSocketProvider);
        assertNotNull(serverSocketProvider.getSocket());
        assertEquals(true, serverSocketProvider.getSocket().isClosed());
    }

    @Test(expectedExceptions = UnknownHostException.class)
    public void createServerSocketFailedTest() throws IOException {
        BSServerSocketProvider serverSocketProvider = new BSServerSocketProvider();
        String WRONG_IP = "sadsd";
        serverSocketProvider.createSocket(WRONG_IP, PORT);
        serverSocketProvider.close();
        assertNotNull(serverSocketProvider);
        assertNotNull(serverSocketProvider.getSocket());
        assertEquals(true, serverSocketProvider.getSocket().isClosed());
    }
}
