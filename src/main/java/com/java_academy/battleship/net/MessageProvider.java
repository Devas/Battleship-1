package com.java_academy.battleship.net;

import java.io.PipedOutputStream;

/**
 * Created by Siarhei Shauchenka on 17.07.17.
 */
public class MessageProvider {
    private PipedOutputStream firstPlayerOUT;
    private PipedOutputStream secondPlayerOUT;

    public MessageProvider() {
        firstPlayerOUT = new PipedOutputStream();
        secondPlayerOUT = new PipedOutputStream();
    }


}
