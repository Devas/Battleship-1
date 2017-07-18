package com.java_academy.battleship.net.socket_provider;

import com.java_academy.battleship.net.socket_processor.ClientSocketInputProcessor;
import com.java_academy.battleship.net.socket_processor.ClientSocketOutputProcessor;
import com.java_academy.battleship.net.socket_processor.core.InputSocketProcessor;
import com.java_academy.battleship.net.socket_processor.core.OutputSocketProcessor;
import com.java_academy.battleship.net.socket_provider.core.SocketProvider;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.function.Supplier;

import static com.java_academy.battleship.net.ConnectionProvider.executorService;

/**
 * Created by Siarhei Shauchenka on 15.07.2017.
 * <p>
 * Implementation of {@link SocketProvider} for ClientApplication
 */
public class ClientSocketProvider implements SocketProvider<Socket> {

    private Socket socket;
    private OutputSocketProcessor outputProcessor;

    public ClientSocketProvider(Supplier<Socket> supplier) {
        this.socket = supplier.get();
    }

    @Override
    public boolean isClosed() {
        return socket == null || socket.isClosed();
    }

    @Override
    public void close() throws IOException {
        outputProcessor.closeSocket();
    }

    @Override
    public void openSocketConnection(InetSocketAddress inetSocketAddress)  {
        if (!socket.isConnected()) {
            try {
                socket.connect(inetSocketAddress);
            } catch (IOException e) {
                e.printStackTrace();
            }
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

            executorService.execute(inputProcessor);
    }

    @Override
    public void sendMessage(String string) {
        outputProcessor.sendMessage(string);
    }
}
