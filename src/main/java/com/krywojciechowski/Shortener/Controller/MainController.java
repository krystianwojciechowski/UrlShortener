package com.krywojciechowski.Shortener.Controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krywojciechowski.Shortener.Controller.Response.Enum.Status;
import com.krywojciechowski.Shortener.Controller.Response.Response;
import com.krywojciechowski.Shortener.UrlShortener.SimpleUrlShortener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "url")
public class MainController {

    @Autowired
    SimpleUrlShortener shortener;

    @RequestMapping(value="/shorten",method = RequestMethod.GET )
    public @ResponseBody Response shorten() throws URISyntaxException {

        return new Response(Status.OK,shortener.shorten("www.google.com"));
    }

}
