package com.krywojciechowski.Shortener.Controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krywojciechowski.Shortener.Controller.Response.Enum.Status;
import com.krywojciechowski.Shortener.Controller.Response.Response;
import com.krywojciechowski.Shortener.Entity.ShortenedUrl;
import com.krywojciechowski.Shortener.Repository.IShortenedUrlDAO;
import com.krywojciechowski.Shortener.Repository.ShortenedUrlDAO;
import com.krywojciechowski.Shortener.UrlShortener.SimpleUrlShortener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

@RestController
@RequestMapping(value = "url")
public class MainController {

    @Autowired
    private SimpleUrlShortener shortener;

    @Autowired
    private ShortenedUrlDAO urlDao;

    @RequestMapping(value="/shorten/{longUrl}",method = RequestMethod.GET )
    public @ResponseBody Response shorten(@PathVariable  String longUrl) throws URISyntaxException {

        ShortenedUrl shortUrl;
        do {
            shortUrl = new ShortenedUrl(shortener.shorten(longUrl),longUrl,new Date());
        }
        while(urlDao.find(shortUrl) != null);
        this.urlDao.insert(shortUrl);
        return new Response(Status.OK,this.urlDao.count(new ShortenedUrl())+" "+shortener.shorten("www.google.com"));
    }

}
