package com.krywojciechowski.Shortener.Controller;

import com.krywojciechowski.Shortener.Controller.Response.Enum.Status;
import com.krywojciechowski.Shortener.Controller.Response.Response;
import com.krywojciechowski.Shortener.Dao.IShortenedUrlDao;
import com.krywojciechowski.Shortener.Dao.IVisitHistoryDao;
import com.krywojciechowski.Shortener.Entity.ShortenedUrl;
import com.krywojciechowski.Shortener.Entity.VisitHistory;
import com.krywojciechowski.Shortener.cache.manager.CacheManager;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.RedirectView;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.cache.ICacheManager;
import reactor.core.publisher.Mono;
import java.util.Date;

@Controller
public class MainController {

    @Autowired
    private IVisitHistoryDao visitHistoryDao;
    @Autowired
    private IShortenedUrlDao shortenedUrlDao;
    @Autowired
    private CacheManager cacheManager;

    @GetMapping(value = "/{hash}")
    @ResponseStatus(code = HttpStatus.TEMPORARY_REDIRECT)
    public RedirectView  redirect(@PathVariable String hash){
        VisitHistory visitHistory = new VisitHistory(hash,new Date());
        this.visitHistoryDao.insert(visitHistory);
        return this.getRedirectView(this.getUrl(hash));
    }

    private RedirectView getRedirectView(String url){
        RedirectView redirectView = new RedirectView();

        if(url != null){
            redirectView.setUrl(url);
        } else {
            redirectView.setUrl("404");
        }

        return redirectView;
    }
    private String getUrl(String hash){
        String url = cacheManager.retrieveFromCache(hash);
        if(url == null){
            ShortenedUrl shortenedUrl = shortenedUrlDao.find(hash);
            if(shortenedUrl!=null){
               url = shortenedUrl.getUrl();
            }
        }
        return url;
    }

    @GetMapping(value = "404")
    public @ResponseBody Response notFound(){
        return new Response(Status.NOT_FOUND, "Sorry, we couldn't find requested resource!");
    }
}
