package ru.geekbrains.Hendlers;

import ru.geekbrains.Loggers.ConsoleLogger;
import ru.geekbrains.Loggers.Logger;
import ru.geekbrains.Services.SocketService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RequestHandler implements Runnable {

    private static final String WWW = "D:/Java_GB_6_Architecture/Lesson_1/geek-architecture-1/www";
    private static final Logger logger = new ConsoleLogger();
    private final SocketService socketService;

    public RequestHandler(SocketService socketService) {
        this.socketService = socketService;
    }

    @Override
    public void run(){

        List<String> request = socketService.readRequest();

            String[] parts = request.get(0).split(" ");

            Path path = Paths.get(WWW, parts[1]);
            if (!Files.exists(path)) {
               socketService.writeResponse("HTTP/1.1 404 NOT_FOUND\n" +
                "Content-Type: text/html; charset=utf-8\n" +
                               "\n",
                new BufferedReader(new StringReader("<h1>Файл не найден!</h1>\n"))
               );
                return;
            }


        try {
            socketService.writeResponse("HTTP/1.1 200 OK\n" +
                    "Content-Type: text/html; charset=utf-8\n" +
                    "\n", Files.newBufferedReader(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.info("Client disconnected!");

    }

}
