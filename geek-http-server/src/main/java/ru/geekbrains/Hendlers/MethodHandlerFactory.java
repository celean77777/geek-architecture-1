package ru.geekbrains.Hendlers;

import ru.geekbrains.Services.SocketService;
import ru.geekbrains.config.Config;
import ru.geekbrains.serializers.ResponseSerializer;

public class MethodHandlerFactory {
    public static MethodHandler create(SocketService socketService, ResponseSerializer serializer, Config config){
        PostMethodHandler postMethodHandler = new PostMethodHandler("POST", null, socketService, serializer, config);
        return new GetMethodHandler("GET", postMethodHandler, socketService, serializer, config);
    }

}
