package ru.geekbrains.Services;

import ru.geekbrains.Loggers.ConsoleLogger;
import ru.geekbrains.Loggers.Logger;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SocketService implements Closeable {

    private final Socket socket;
    private static final Logger logger = new ConsoleLogger();

    public Deque<String> readRequest() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream(), StandardCharsets.UTF_8));
            while (!input.ready());

            Deque<String> request = new LinkedList<>();
            while (input.ready()){
                String line = input.readLine();
                logger.info(line);
                request.add(line);
            }
            return request;
        }catch (IOException e){
            throw new IllegalStateException(e);
        }

    }

    public void writeResponse(String headers){
        try {
            PrintWriter output = new PrintWriter(socket.getOutputStream());
            output.print(headers);
            output.flush();
        }catch (IOException e){
            throw new IllegalStateException(e);
        }
    }

    private SocketService(Socket socket) {
        this.socket = socket;
    }

    public static SocketService createSocketService(Socket socket){
        return new SocketService(socket);
    }


    @Override
    public void close() throws IOException {
        if (!socket.isClosed()){
            socket.close();
        }
    }
}
