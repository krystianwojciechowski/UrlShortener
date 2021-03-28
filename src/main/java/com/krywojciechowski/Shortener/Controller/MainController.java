package com.krywojciechowski.Shortener.Controller;

import com.krywojciechowski.Shortener.Controller.Response.Enum.Status;
import com.krywojciechowski.Shortener.Controller.Response.Response;
import com.krywojciechowski.Shortener.Dao.IShortenedUrlDAO;
import com.krywojciechowski.Shortener.Entity.ShortenedUrl;
import com.krywojciechowski.Shortener.UrlShortener.IUrlShortener;
import com.krywojciechowski.Shortener.cache.manager.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "url")
public class MainController {

    @Autowired
    private IUrlShortener shortener;

    @Autowired
    private IShortenedUrlDAO urlDao;

    @Autowired
    private CacheManager cacheManager;

    @RequestMapping(value="/shorten/{longUrl}",method = RequestMethod.GET )
    public @ResponseBody Response shorten(@PathVariable  String longUrl) throws URISyntaxException {
        ShortenedUrl shortUrl;
        shortUrl = new ShortenedUrl(shortener.shorten(longUrl),longUrl,new Date());
//        do {
//            shortUrl = new ShortenedUrl(shortener.shorten(longUrl),longUrl,new Date());
//        }
//        while( urlDao.find(shortUrl)!= null);
        this.urlDao.insert(shortUrl);
        return new Response(Status.OK,urlDao.find(shortUrl));
    }

}
