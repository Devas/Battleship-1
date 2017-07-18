package com.java_academy.battleship.net.socket_processor.core;

/**
 * Created by Siarhei Shauchenka on 18.07.17.
 */
public interface InputSocketProcessor extends SocketProcessor{
    void setListener(final SocketProcessorListener processorListener);
}
