package com.jawapbo.sijiusu.utils;

public enum Endpoint {
    LOGIN("/auth/login")

    ;

    private final String path;
    Endpoint(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
