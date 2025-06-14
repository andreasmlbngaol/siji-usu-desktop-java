package com.jawapbo.sijiusu.utils;

public enum AppScene {
    LOGIN("login-view", "Login Scene"),
    ADMIN_DASHBOARD("admin-dashboard", "Admin Dashboard"),
    STUDENT_PROFILE("student-profile", "Student Profile"),

    ;

    private final String fxml;
    private final String title;

    public static final int width = 1280;
    public static final int height = 720;


    AppScene(String fxml, String title) {
        this.fxml = String.format("views/%s.fxml", fxml);
        this.title = title;
    }

    public String getFxml() {
        return fxml;
    }

    public String getTitle() {
        return title;
    }
}
