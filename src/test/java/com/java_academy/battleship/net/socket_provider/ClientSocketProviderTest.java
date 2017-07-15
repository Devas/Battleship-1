package com.java_academy.battleship.net.socket_provider;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertNotNull;

/**
 * Created by Siarhei Shauchenka on 15.07.2017.
 * <p>
 * Tests for ClientProvider class
 */
@Test
public class ClientSocketProviderTest {

    public void clientSocketProviderCreationTest(){
        ClientSocketProvider clientSocketProvider = new ClientSocketProvider();
        assertNotNull(clientSocketProvider);
    }
}
