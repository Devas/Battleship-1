package com.java_academy.battleship.net.socket_processor.core;

import java.net.Socket;

/**
 * Created by Siarhei Shauchenka on 16.07.2017.
 * <p>
 */
public interface SocketProcessorListener  {
    void messageReceived(String message);
}
