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
 * Implementation of {@link SocketProvider} for ClientApplication
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
        getSocket().close();
    }

    @Override
    public Socket openSocketConnection(InetSocketAddress inetSocketAddress) throws IOException {
        if (!getSocket().isConnected()) {
            getSocket().connect(inetSocketAddress);
        }
        return getSocket();
    }

    @Override
    public boolean processConnection(Supplier<SocketProcessor> supplier, InetSocketAddress inetSocketAddress) {
        try {
            Socket socket = openSocketConnection(inetSocketAddress);
            SocketProcessor processor = supplier.get();
            processor.setSocket(socket);
            processor.setListener(() -> System.out.println("Connection is under process!"));
            new Thread(processor).start();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
