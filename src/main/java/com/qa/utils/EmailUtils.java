package com.qa.utils;

public class EmailUtils {

    public static String generateEmail()
    {
        String emaiId = "naveen"+System.currentTimeMillis()+"@open.com";
        return emaiId;
    }
}
