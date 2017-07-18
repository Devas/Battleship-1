package com.java_academy.battleship.net.socket_provider;

import com.java_academy.battleship.net.socket_processor.ClientSocketOutputProcessor;
import com.java_academy.battleship.net.socket_processor.ClientSocketInputProcessor;
import com.java_academy.battleship.net.socket_processor.core.InputSocketProcessor;
import com.java_academy.battleship.net.socket_processor.core.OutputSocketProcessor;
import com.java_academy.battleship.net.socket_processor.core.SocketProcessor;
import com.java_academy.battleship.net.socket_processor.core.SocketProcessorListener;
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
    private OutputSocketProcessor outputProcessor;

    public ClientSocketProvider() {
        this.socket = new Socket();
    }

    @Override
    public Socket getSocket() {
        return socket;
    }

    @Override
    public void close() throws IOException {
        outputProcessor.closeSocket();
    }

    @Override
    public void openSocketConnection(InetSocketAddress inetSocketAddress) throws IOException {
        if (!getSocket().isConnected()) {
            getSocket().connect(inetSocketAddress);
        }
        processConnection(ClientSocketOutputProcessor::new, ClientSocketInputProcessor::new);
    }

    @Override
    public void processConnection(Supplier<OutputSocketProcessor> outputProcessorSupplier, Supplier<InputSocketProcessor> inputProcessorSupplier) {
            outputProcessor = outputProcessorSupplier.get();
            outputProcessor.setSocket(socket);

            InputSocketProcessor inputProcessor = inputProcessorSupplier.get();
            inputProcessor.setSocket(socket);
            inputProcessor.setListener(message -> System.out.println("message from server = " + message));

            new Thread(inputProcessor).start();
    }

    @Override
    public void sendMessage(String string) {
        outputProcessor.sendMessage(string);
    }
}
