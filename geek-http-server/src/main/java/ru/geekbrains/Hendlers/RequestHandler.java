package ru.geekbrains.Hendlers;

import ru.geekbrains.Loggers.ConsoleLogger;
import ru.geekbrains.Loggers.Logger;
import ru.geekbrains.Services.SocketService;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.parsers.RequestParser;
import java.io.*;
import java.util.Deque;

public class RequestHandler implements Runnable {

    private static final Logger logger = new ConsoleLogger();
    private final SocketService socketService;
    private final RequestParser parser;
    private final MethodHandler methodHandler;

    public RequestHandler(SocketService socketService,
                          RequestParser parser,
                          MethodHandler methodHandler
                         ) {
        this.socketService = socketService;
        this.parser = parser;
        this.methodHandler = methodHandler;
    }

    @Override
    public void run(){

        Deque<String> request = socketService.readRequest();
        HttpRequest httpRequest = parser.parseRequest(request);

        methodHandler.handle(httpRequest);

        try {
            socketService.close();
        }catch (IOException e){
            throw new IllegalStateException(e);
        }
        logger.info("Client disconnected!");
  }

    }

