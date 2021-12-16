package com.jamesdube.tests.template.utils;

import com.google.gson.Gson;

public class Utils {

    private static final Gson gson = new Gson();

    public static String toJson(Object o){
        return gson.toJson(o);
    }
}
