package ru.geekbrains.parsers;

import ru.geekbrains.domain.HttpRequest;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Parser implements RequestParser {
    @Override
    public HttpRequest parseRequest(Deque<String> rawRequest){
        String[] topLine = rawRequest.pollFirst().split(" ");
        String method = topLine[0];
        String path = topLine[1];
        String protocol = topLine[2];

        Map<String, String> headers = new HashMap<>();
        while (!rawRequest.isEmpty()){
            String line = rawRequest.pollFirst();
            if(line.isBlank()){
                break;
            }
            String[] header = line.split(": ");
            headers.put(header[0], header[1]);
        }
        StringBuilder body = new StringBuilder();
        while (!rawRequest.isEmpty()){
            body.append(rawRequest.pollFirst());
        }

        return HttpRequest.createBuilder()
                .withMethod(method)
                .withPath(path)
                .withProtocol(protocol)
                .withHeaders(headers)
                .withBody(body.toString())
                .build();

    }

}
