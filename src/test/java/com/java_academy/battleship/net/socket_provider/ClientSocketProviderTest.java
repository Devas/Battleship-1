package com.java_academy.battleship.net.socket_provider;

import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * Created by Siarhei Shauchenka on 15.07.2017.
 * <p>
 * Tests for ClientProvider class
 */
@Test
public class ClientSocketProviderTest {

    public void creationTest() {
        ClientSocketProvider clientSocketProvider = new ClientSocketProvider();
        assertNotNull(clientSocketProvider);
    }

    public void closingTest() {
        ClientSocketProvider clientSocketProvider = new ClientSocketProvider();
        try {
            clientSocketProvider.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(clientSocketProvider.getSocket().isClosed(), true);
    }

}
