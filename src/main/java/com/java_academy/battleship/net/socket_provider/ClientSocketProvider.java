package com.java_academy.battleship.net.socket_provider;

import com.java_academy.battleship.net.socket_processor.core.SocketProcessor;
import com.java_academy.battleship.net.socket_provider.core.SocketProvider;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.function.Supplier;

/**
 * Created by Siarhei Shauchenka on 15.07.2017.
 * <p>
 */
public class ClientSocketProvider implements SocketProvider<Socket> {

    private Socket socket;

    public ClientSocketProvider() {
        this.socket = new Socket();
    }

    @Override
    public Socket getSocket() {
        return socket;
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }

    @Override
    public Socket openSocketConnection(InetSocketAddress inetSocketAddress) throws IOException {
        if (!socket.isConnected()) {
            socket.connect(inetSocketAddress);
        }
        return socket;
    }

    @Override
    public void processConnection(Supplier<SocketProcessor> supplier, InetSocketAddress inetSocketAddress) {
        try {
            Socket socket = openSocketConnection(inetSocketAddress);
            SocketProcessor processor = supplier.get();
            processor.setSocket(socket);
            new Thread(processor).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
