package com.krywojciechowski.Shortener.UrlShortener;


import org.apache.commons.lang3.RandomStringUtils;
import org.bouncycastle.jcajce.provider.digest.MD5;
import org.springframework.stereotype.Component;
import reactor.blockhound.shaded.net.bytebuddy.utility.RandomString;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SimpleUrlShortener extends UrlShortener{


    @Override
    public String shorten(String url) {
       return RandomStringUtils.randomAlphabetic(8);
    }
}
