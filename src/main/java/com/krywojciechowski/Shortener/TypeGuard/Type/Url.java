package com.krywojciechowski.Shortener.TypeGuard.Type;

import java.net.MalformedURLException;
import java.net.URL;

public class Url extends Type{

    @Override
    public boolean isCorrect(String value) {
        try {
            URL url = new URL(value);
        }
        catch (MalformedURLException exception){
            return false;
        }
        return true;
    }
}
