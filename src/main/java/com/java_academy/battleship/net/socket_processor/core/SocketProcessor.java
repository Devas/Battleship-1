package com.java_academy.battleship.net.socket_processor.core;

import java.net.Socket;

/**
 * Created by Siarhei Shauchenka on 15.07.2017.
 * <p>
 */
public interface SocketProcessor extends Runnable{
    void setSocket(final Socket ... socket);
    void closeSocket();
}
