package ru.geekbrains;

import ru.geekbrains.Hendlers.MethodHandlerFactory;
import ru.geekbrains.Hendlers.RequestHandler;
import ru.geekbrains.Loggers.ConsoleLogger;
import ru.geekbrains.Loggers.Logger;
import ru.geekbrains.Services.SocketService;
import ru.geekbrains.Services.SocketServiceFactory;
import ru.geekbrains.config.Config;
import ru.geekbrains.config.ConfigFactory;
import ru.geekbrains.parsers.RequestParserFactory;
import ru.geekbrains.serializers.ResponseSerializer;
import ru.geekbrains.serializers.ResponseSerializerFactory;
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

                SocketService socketService = SocketServiceFactory.createSocketService(socket);
                ResponseSerializer responseSerializer = ResponseSerializerFactory.createResponseSerializer();


                new Thread(new RequestHandler(
                        socketService
                        , RequestParserFactory.createRequestParser()
                        , MethodHandlerFactory.create(socketService, responseSerializer, config))).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
