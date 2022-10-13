package ru.geekbrains.parsers;

public class RequestParserFactory {
    public static RequestParser createRequestParser(){
        return new ParserImpl();
    }
}
