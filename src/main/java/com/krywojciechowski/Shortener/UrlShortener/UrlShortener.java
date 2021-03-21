package com.krywojciechowski.Shortener.UrlShortener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


public abstract class UrlShortener {
    public abstract String shorten(String url);

}
