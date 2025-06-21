package com.jawapbo.sijiusu.utils;

public enum Endpoint {
    LOGIN("/auth/login"),
    GET_ADMIN_INFO("/admins"),
    ADMIN_LECTURERS("/admins/users/lecturers"),
    ADMIN_STUDENTS("/admins/users/students"),
    CHANGE_PASSWORD("/auth/password"),
    ADMIN_GET_FACULTIES("/admins/academic/faculties"),
    ADMIN_GET_FACULTY_BY_ID("/admins/academic/faculties/%d"),
    ADMIN_GET_MAJORS("/admins/academic/faculties/majors"),
    ADMIN_GET_COURSES_BY_MAJOR_ID("/admins/academic/majors/%d/courses"),
    ADMIN_GET_COURSE_BY_ID("/admins/academic/majors/courses/%d"),
    ADMIN_GET_CREATE_COURSE_WITH_MAJOR_ID("/admins/academic/majors/%d/courses"),

    GET_STUDENT_INFO("/students"),
    GET_LECTURER_INFO("/lecturers"),
    ;

    private final String path;
    Endpoint(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
