package com.krywojciechowski.Shortener.Controller;

import com.krywojciechowski.Shortener.Controller.Response.Enum.Status;
import com.krywojciechowski.Shortener.Controller.Response.Response;
import com.krywojciechowski.Shortener.Entity.ShortenedUrl;
import com.krywojciechowski.Shortener.Repository.IShortenedUrlDAO;
import com.krywojciechowski.Shortener.UrlShortener.IUrlShortener;
import com.krywojciechowski.Shortener.cache.ICacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Date;

@RestController
@RequestMapping(value = "url")
public class MainController {

    @Autowired
    private IUrlShortener shortener;

    @Autowired
    private IShortenedUrlDAO urlDao;

    @Autowired
    private ICacheManager cacheManager;
    @RequestMapping(value="/shorten/{longUrl}",method = RequestMethod.GET )
    public @ResponseBody Response shorten(@PathVariable  String longUrl) throws URISyntaxException {

//        ShortenedUrl shortUrl;
//        do {
//            shortUrl = new ShortenedUrl(shortener.shorten(longUrl),longUrl,new Date());
//        }
//        while(urlDao.find(shortUrl) != null);
//        this.urlDao.insert(shortUrl);
        this.cacheManager.getConnection().set("key", shortener.shorten("www.google.com").toString());
        return new Response(Status.OK,"body");
    }

}
