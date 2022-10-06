package ru.geekbrains.serializers;

import ru.geekbrains.domain.HttpResponse;

public interface ResponseSerializer {
    String serialize(HttpResponse httpResponse);

}
