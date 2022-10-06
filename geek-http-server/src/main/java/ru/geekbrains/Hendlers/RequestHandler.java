package ru.geekbrains.Hendlers;

import ru.geekbrains.Loggers.ConsoleLogger;
import ru.geekbrains.Loggers.Logger;
import ru.geekbrains.Services.SocketService;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.parsers.Parser;
import ru.geekbrains.serializers.Serializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Deque;

public class RequestHandler implements Runnable {

    private static final String WWW = "D:\\Java_GB_6_Architecture\\Lesson_1\\geek-architecture-1\\www";
    private static final Logger logger = new ConsoleLogger();
    private final SocketService socketService;
    private final Parser parser;
    private final Serializer serializer;

    public RequestHandler(SocketService socketService, Parser parser, Serializer serializer) {
        this.socketService = socketService;
        this.parser = parser;
        this.serializer = serializer;
    }

    @Override
    public void run(){

        Deque<String> request = socketService.readRequest();
        HttpRequest httpRequest = parser.parseRequest(request);

        if (httpRequest.getMethod().equals("GET")){
            Path path = Paths.get(WWW, httpRequest.getPath());

            if (!Files.exists(path)){
                HttpResponse httpResponse = new HttpResponse("HTTP/1.1", 404,
                        "NOT_FOUND", "Content-Type: text/html; charset=utf-8");
               socketService.writeResponse(serializer.serialize(httpResponse));
            }else {
                StringBuilder body = new StringBuilder();
                try {
                    Files.readAllLines(path).forEach(body::append);
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
                HttpResponse httpResponse = new HttpResponse("HTTP/1.1", 200,
                        "OK", "Content-Type: text/html; charset=utf-8", body.toString());
                socketService.writeResponse(serializer.serialize(httpResponse));
            }

        }


//            if (!Files.exists(path)) {
//               socketService.writeResponse("HTTP/1.1 404 NOT_FOUND\n" +
//                "Content-Type: text/html; charset=utf-8\n" +
//                               "\n",
//                new BufferedReader(new StringReader("<h1>Файл не найден!</h1>\n"))
//               );
//                return;
//            }


//        try {
//            socketService.writeResponse("HTTP/1.1 200 OK\n" +
//                    "Content-Type: text/html; charset=utf-8\n" +
//                    "\n", Files.newBufferedReader(path));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        logger.info("Client disconnected!");

    }

}
