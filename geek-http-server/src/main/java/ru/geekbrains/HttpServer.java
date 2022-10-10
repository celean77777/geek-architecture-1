package ru.geekbrains;

import ru.geekbrains.Hendlers.RequestHandler;
import ru.geekbrains.Loggers.ConsoleLogger;
import ru.geekbrains.Loggers.Logger;
import ru.geekbrains.Services.SocketService;
import ru.geekbrains.config.Config;
import ru.geekbrains.config.ConfigFactory;
import ru.geekbrains.parsers.Parser;
import ru.geekbrains.serializers.Serializer;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class HttpServer {
    private static final Logger logger = new ConsoleLogger();
    public static void main(String[] args) {
        Config config = ConfigFactory.create(args, "./../../../Server.prop");

        try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
            logger.info("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("New client connected!");
                new Thread(new RequestHandler(SocketService.createSocketService(socket),
                        new Parser(), new Serializer(), config)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
