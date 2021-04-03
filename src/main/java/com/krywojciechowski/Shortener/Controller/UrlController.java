package com.krywojciechowski.Shortener.Controller;

import com.krywojciechowski.Shortener.Controller.Response.Enum.Status;
import com.krywojciechowski.Shortener.Controller.Response.Response;
import com.krywojciechowski.Shortener.Dao.IShortenedUrlDao;
import com.krywojciechowski.Shortener.Entity.ShortenedUrl;
import com.krywojciechowski.Shortener.UrlShortener.IUrlShortener;
import com.krywojciechowski.Shortener.cache.manager.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Date;

@RestController
@RequestMapping(value = "url")
public class UrlController {

    @Autowired
    private IUrlShortener shortener;

    @Autowired
    private IShortenedUrlDao urlDao;

    @Autowired
    private CacheManager cacheManager;

    @RequestMapping(value="/shorten/{longUrl}",method = RequestMethod.GET )
    public @ResponseBody Response shorten(@PathVariable  String longUrl) throws URISyntaxException {
        String shortUrl = null;
        do {
            shortUrl = shortener.shorten(longUrl);
        }
        while( urlDao.find(shortUrl)!= null);
        ShortenedUrl shortenedUrlObject = new ShortenedUrl(shortUrl,longUrl,new Date());
        this.urlDao.insert(shortenedUrlObject);
        return new Response(Status.OK,shortenedUrlObject.getSafeData());
    }

}
