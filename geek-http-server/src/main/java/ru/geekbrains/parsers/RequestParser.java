package ru.geekbrains.parsers;

import ru.geekbrains.domain.HttpRequest;

import java.util.Deque;

public interface RequestParser {
    HttpRequest parseRequest(Deque<String> rawRequest);
}
