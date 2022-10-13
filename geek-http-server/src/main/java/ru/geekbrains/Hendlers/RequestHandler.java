package ru.geekbrains.Hendlers;

import ru.geekbrains.Loggers.ConsoleLogger;
import ru.geekbrains.Loggers.Logger;
import ru.geekbrains.Services.SocketService;
import ru.geekbrains.config.Config;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.parsers.RequestParser;
import ru.geekbrains.serializers.ResponseSerializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Deque;

public class RequestHandler implements Runnable {

    private static final Logger logger = new ConsoleLogger();
    private final SocketService socketService;
    private final RequestParser parser;
    private final ResponseSerializer serializer;
    private final Config config;

    public RequestHandler(SocketService socketService, RequestParser parser, ResponseSerializer serializer, Config config) {
        this.socketService = socketService;
        this.parser = parser;
        this.serializer = serializer;
        this.config = config;
    }

    @Override
    public void run(){

        Deque<String> request = socketService.readRequest();
        HttpRequest httpRequest = parser.parseRequest(request);

        if (httpRequest.getMethod().equals("GET")){
            Path path = Paths.get(config.getWwwHome(), httpRequest.getPath());

            if (!Files.exists(path)){
                HttpResponse httpResponse = HttpResponse.createBuilder()
                        .withProtocol("HTTP/1.1")
                        .withStatusCode(404)
                        .withStatement( "NOT_FOUND")
                        .withContentType("Content-Type: text/html; charset=utf-8")
                        .withBody("File not found!")
                        .build();
               socketService.writeResponse(serializer.serialize(httpResponse));
            }else {
                StringBuilder body = new StringBuilder();
                try {
                    Files.readAllLines(path).forEach(body::append);
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
                HttpResponse httpResponse = HttpResponse.createBuilder()
                        .withProtocol("HTTP/1.1")
                        .withStatusCode(200)
                        .withStatement("OK")
                        .withContentType("Content-Type: text/html; charset=utf-8")
                        .withBody(body.toString())
                        .build();
                socketService.writeResponse(serializer.serialize(httpResponse));
            }

        }

        logger.info("Client disconnected!");

    }

}
