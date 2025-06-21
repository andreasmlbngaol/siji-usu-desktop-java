package com.jawapbo.sijiusu.utils;

public enum Endpoint {
    LOGIN("/auth/login"),
    GET_ADMIN_INFO("/admins"),
    ADMIN_LECTURERS("/admins/users/lecturers"),
    ADMIN_GET_STUDENTS("/admins/users/students"),
    CHANGE_PASSWORD("/auth/password"),
    ADMIN_GET_FACULTIES("/admins/academic/faculties"),
    ADMIN_GET_FACULTY_BY_ID("/admins/academic/faculties/%d"),

    ;

    private final String path;
    Endpoint(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
