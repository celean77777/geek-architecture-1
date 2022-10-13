package ru.geekbrains.serializers;

import ru.geekbrains.domain.HttpResponse;

class SerializerImpl implements ResponseSerializer{
    @Override
    public String serialize(HttpResponse httpResponse){
        StringBuilder response = new StringBuilder();

        response.append(httpResponse.getProtocol() + " ");
        response.append(httpResponse.getStatusCode() + " ");
        response.append(httpResponse.getStatement());
        response.append("\n");
        response.append(httpResponse.getContentType());
        response.append("\n");
        response.append("\n");
        response.append(httpResponse.getBody());

        return response.toString();




    }

}
