package com.java_academy.battleship.net.socket_processor.core;

import java.io.PipedOutputStream;
import java.net.Socket;

/**
 * Created by Siarhei Shauchenka on 15.07.2017.
 * <p>
 */
public interface SocketProcessor extends Runnable{
    void setSocket(final Socket socket);
    void setListener(final SocketProcessorListener processorListener);
    void setOutputPipe(PipedOutputStream outputPipe);
}
