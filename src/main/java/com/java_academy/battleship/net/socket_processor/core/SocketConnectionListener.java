package com.java_academy.battleship.net.socket_processor.core;

import java.net.Socket;

/**
 * Created by Siarhei Shauchenka on 18.07.17.
 */
public interface SocketConnectionListener {
    void socketConnected(Socket socket);
}
