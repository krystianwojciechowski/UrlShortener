package com.krywojciechowski.Shortener.UrlShortener;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class SimpleUrlShortener implements IUrlShortener {


    @Override
    public String shorten(String url) {
       return RandomStringUtils.randomAlphabetic(8);
    }
}
