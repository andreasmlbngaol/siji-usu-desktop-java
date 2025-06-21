package com.jawapbo.sijiusu.utils;

public enum Endpoint {
    LOGIN("/auth/login"),
    GET_ADMIN("/admins"),
    CHANGE_PASSWORD("/auth/password")

    ;

    private final String path;
    Endpoint(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
