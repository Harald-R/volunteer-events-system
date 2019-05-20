package com.pad.cristina.freely.model;

public class UserInfo {
    private static String token=null;

    public static void setToken(String token) {
        UserInfo.token = token;
    }

    public static String getToken() {
        return token;
    }
}
