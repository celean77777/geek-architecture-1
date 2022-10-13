package ru.geekbrains.serializers;

public class ResponseSerializerFactory {

    public static ResponseSerializer createResponseSerializer(){
        return new SerializerImpl();
    }
}
