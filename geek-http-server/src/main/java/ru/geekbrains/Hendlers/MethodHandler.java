package ru.geekbrains.Hendlers;

import ru.geekbrains.Services.SocketService;
import ru.geekbrains.config.Config;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.serializers.ResponseSerializer;


public abstract class MethodHandler {

        private final String method;
        private final ru.geekbrains.Hendlers.MethodHandler next;
        protected final SocketService socketService;
        protected final ResponseSerializer serializer;
        protected final Config config;

        public MethodHandler(String method, MethodHandler next, SocketService socketService, ResponseSerializer serializer, Config config) {
            this.method = method;
            this.next = next;
            this.socketService = socketService;
            this.serializer = serializer;
            this.config = config;
        }

        public void handle (HttpRequest httpRequest){
            HttpResponse httpResponse;
            if (method.equals(httpRequest.getMethod())){
                httpResponse = internalHandle(httpRequest);
            } else if (next != null){
                next.handle(httpRequest);
                return;
            } else {
                httpResponse = HttpResponse.createBuilder()
                        .withProtocol("HTTP/1.1")
                        .withStatusCode(405)
                        .withStatement( "METHOD_NOT_ALLOWED")
                        .withContentType("Content-Type: text/html; charset=utf-8")
                        .withBody("Method not allowed!")
                        .build();
            }
            socketService.writeResponse(serializer.serialize(httpResponse));

        }

        protected abstract HttpResponse internalHandle(HttpRequest httpRequest);
    }


