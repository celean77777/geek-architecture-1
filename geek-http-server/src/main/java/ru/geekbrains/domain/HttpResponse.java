package ru.geekbrains.domain;

import java.nio.file.Path;

public class HttpResponse {
    private String protocol;
    private int statusCode;
    private String statement;
    private String contentType;
    private String body;

    private HttpResponse(){

    }

    public String getStatement() {
        return statement;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getContentType() {
        return contentType;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getBody() {
        return body;
    }

    public static Builder createBuilder(){
        return new Builder();
    }

    public static class Builder{
        private final HttpResponse httpResponse;

        private Builder(){
            this.httpResponse = new HttpResponse();
        }

        public Builder withProtocol(String protocol){
            this.httpResponse.protocol = protocol;
            return this;
        }

        public Builder withStatusCode(int statusCode){
            this.httpResponse.statusCode = statusCode;
            return this;
        }

        public Builder withStatement(String statement){
            this.httpResponse.statement = statement;
            return this;
        }

        public Builder withContentType(String contentType){
            this.httpResponse.contentType = contentType;
            return this;
        }

        public Builder withBody(String body){
            this.httpResponse.body = body;
            return this;
        }

        public HttpResponse build(){
            return this.httpResponse;
        }


    }

}
