package ru.geekbrains.Hendlers;

import ru.geekbrains.Services.SocketService;
import ru.geekbrains.config.Config;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.serializers.ResponseSerializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Handler(order = 0, method = "GET")

public class GetMethodHandler extends MethodHandler{

    public GetMethodHandler(String method, MethodHandler next, SocketService socketService,
                            ResponseSerializer serializer, Config config) {
        super(method, next, socketService, serializer, config);
    }

    @Override
    protected HttpResponse internalHandle(HttpRequest httpRequest) {
        Path path = Paths.get(config.getWwwHome(), httpRequest.getPath());

        if (!Files.exists(path)){
            return HttpResponse.createBuilder()
                    .withProtocol("HTTP/1.1")
                    .withStatusCode(404)
                    .withStatement( "NOT_FOUND")
                    .withContentType("Content-Type: text/html; charset=utf-8")
                    .withBody("File not found!")
                    .build();
        }else {
            StringBuilder body = new StringBuilder();
            try {
                Files.readAllLines(path).forEach(body::append);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
            return HttpResponse.createBuilder()
                    .withProtocol("HTTP/1.1")
                    .withStatusCode(200)
                    .withStatement("OK")
                    .withContentType("Content-Type: text/html; charset=utf-8")
                    .withBody(body.toString())
                    .build();
        }

    }
}
