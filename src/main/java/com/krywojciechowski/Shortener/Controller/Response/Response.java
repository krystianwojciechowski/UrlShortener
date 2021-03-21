package com.krywojciechowski.Shortener.Controller.Response;

import com.krywojciechowski.Shortener.Controller.Response.Enum.Status;

public record Response(Status status,Object body){
}
