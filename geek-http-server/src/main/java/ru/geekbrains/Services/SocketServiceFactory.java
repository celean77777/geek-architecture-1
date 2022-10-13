package ru.geekbrains.Services;

import java.net.Socket;

public class SocketServiceFactory {

    public static SocketService createSocketService (Socket socket){
        return new SocketServiceImpl(socket);
    }
}
