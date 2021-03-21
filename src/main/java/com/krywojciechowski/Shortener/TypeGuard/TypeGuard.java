package com.krywojciechowski.Shortener.TypeGuard;

import com.krywojciechowski.Shortener.TypeGuard.Type.Type;

public class TypeGuard {

    public boolean isCorrectValueOfType(String value,Type type){
        return type.isCorrect(value);
    }
}
