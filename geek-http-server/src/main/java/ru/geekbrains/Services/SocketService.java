package ru.geekbrains.Services;

import java.io.Closeable;
import java.util.Deque;

public interface SocketService extends Closeable {

    Deque<String> readRequest();
    void writeResponse(String headers);
}
