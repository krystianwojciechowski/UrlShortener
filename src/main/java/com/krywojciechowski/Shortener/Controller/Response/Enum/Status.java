package com.krywojciechowski.Shortener.Controller.Response.Enum;

public enum Status {
    OK(200),
    ACCEPTED(202),
    NOT_FOUND(404),
    BAD_REQUEST(400),
    CONFLICT(409),
    INTERNAL_SERVER_ERROR(500),
    IM_A_TEAPOT(418);

    private final int statusCode;

     Status(int code){
        this.statusCode = code;
    }
}
