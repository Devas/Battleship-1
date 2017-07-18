package com.java_academy.battleship.net.socket_provider;

import com.java_academy.battleship.net.socket_processor.ServerSocketInputProcessor;
import com.java_academy.battleship.net.socket_processor.ServerSocketOutputProcessor;
import com.java_academy.battleship.net.socket_processor.core.InputSocketProcessor;
import com.java_academy.battleship.net.socket_processor.core.OutputSocketProcessor;
import com.java_academy.battleship.net.socket_provider.core.SocketProvider;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Supplier;

import static com.java_academy.battleship.net.ConnectionProvider.executorService;

/**
 * Created by Siarhei Shauchenka on 14.07.17.
 * <p>
 * Provides an access to {@link ServerSocket}
 */

public class ServerSocketProvider implements SocketProvider<ServerSocket> {

    private ServerSocket serverSocket;
    private Socket firstPlayerSocket;
    private Socket secondPlayerSocket;
    private OutputSocketProcessor outputProcessor;

    public ServerSocketProvider(ServerSocket socket) {
        this.serverSocket = socket;
    }

    @Override
    public boolean isClosed() {
        return serverSocket == null || serverSocket.isClosed();
    }

    @Override
    public void close() throws IOException {
        outputProcessor.closeSocket();
        serverSocket.close();
    }

    @Override
    public void openSocketConnection(InetSocketAddress inetSocketAddress) {
        try {
            if (!serverSocket.isBound()) {
                int BACKLOG = 2;
                serverSocket.bind(inetSocketAddress, BACKLOG);
            }
            firstPlayerSocket = serverSocket.accept();
            secondPlayerSocket = serverSocket.accept();
            processConnection(ServerSocketOutputProcessor::new, ServerSocketInputProcessor::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processConnection(Supplier<OutputSocketProcessor> outputProcessorSupplier, Supplier<InputSocketProcessor> inputProcessorSupplier) {
        runInputProcessor(inputProcessorSupplier, firstPlayerSocket);
        runInputProcessor(inputProcessorSupplier, secondPlayerSocket);
        outputProcessor = outputProcessorSupplier.get();
        outputProcessor.setSocket(firstPlayerSocket, secondPlayerSocket);
        //TODO put here some callback to clients to allow them to start sending data
    }

    private void runInputProcessor(Supplier<InputSocketProcessor> inputProcessorSupplier, Socket socket){
        InputSocketProcessor inputSocketProcessor = inputProcessorSupplier.get();
        inputSocketProcessor.setSocket(socket);
        inputSocketProcessor.setListener(message -> {
            System.out.println("message from first client = " + message);
            outputProcessor.sendMessage("echo message from first player = " + message);
        });
        executorService.execute(inputSocketProcessor);
    }

    @Override
    public void sendMessage(String string) {
        outputProcessor.sendMessage(string);
    }
}
