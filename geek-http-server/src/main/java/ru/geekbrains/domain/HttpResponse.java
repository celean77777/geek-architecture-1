package ru.geekbrains.domain;

import java.nio.file.Path;

public class HttpResponse {

    private String protocol;

    private int statusCode;

    private String statement;

    private String contentType;

    private String body;

    public HttpResponse(String protocol, int statusCode, String statement, String contentType){
        this.protocol = protocol;
        this.statement = statement;
        this.statusCode = statusCode;
        this.contentType = contentType;
        this.body = "<h1>Файл не найден!</h1>";
    }

    public HttpResponse(String protocol, int statusCode, String statement, String contentType, String body){
        this.protocol = protocol;
        this.statement = statement;
        this.statusCode = statusCode;
        this.contentType = contentType;
        this.body = body;
    }




    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
