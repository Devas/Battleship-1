package com.java_academy.battleship.net.socket_provider;

import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * Created by Siarhei Shauchenka on 14.07.17.
 *
 * Provides tests for {@link ServerSocketProvider}
 */
@Test
public class ServerSocketProviderTest {

    private final int PORT = 8080;

    public void createServerSocketTest(){
//        ServerSocketProvider serverSocketProvider = new ServerSocketProvider();
//        assertNotNull(serverSocketProvider);
//        try {
//            serverSocketProvider.close();
//            assertEquals(true, serverSocketProvider.isClosed());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void createServerSocketFailedTest() throws IOException {
//        ServerSocketProvider serverSocketProvider = new ServerSocketProvider();
        //TODO implement connection test
    }
}
