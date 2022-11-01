package ru.geekbrains.Hendlers;

import ru.geekbrains.Services.SocketService;
import ru.geekbrains.config.Config;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.serializers.ResponseSerializer;

import java.lang.annotation.Retention;

@Handler(order = 1, method = "POST")
public class PostMethodHandler extends MethodHandler{

        public PostMethodHandler(String method, MethodHandler next, SocketService socketService,
                                ResponseSerializer serializer, Config config) {
            super(method, next, socketService, serializer, config);
        }

    @Override
    protected HttpResponse internalHandle(HttpRequest httpRequest) {
        return HttpResponse.createBuilder()
                .withProtocol("HTTP/1.1")
                .withStatusCode(200)
                .withStatement("OK")
                .withContentType("Content-Type: text/html; charset=utf-8")
                .build();
    }
}
