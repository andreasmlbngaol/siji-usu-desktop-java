package com.jawapbo.sijiusu.utils;

public enum AppScene {
    LOGIN("login-view", "Login Scene"),
    ADMIN_DASHBOARD("admin/admin-dashboard", "Admin Dashboard"),
    ADMIN_DASHBOARD_ACADEMIC("admin/admin-dashboard-academic", "Admin Dashboard Academic"),
    ADMIN_DASHBOARD_USER("admin/admin-dashboard-users", "Admin Dashboard Users"),
    ADMIN_DASHBOARD_USER_LECTURER("admin/admin-dashboard-users-lecturer", "Admin Dashboard Users Lecturer"),
    ADMIN_DASHBOARD_USER_LECTURER_REGISTER("admin/admin-dashboard-users-lecturer-register", "Admin Dashboard Users Lecturer Register"),
    ADMIN_DASHBOARD_USER_STUDENTS("admin/admin-dashboard-users-students", "Admin Dashboard Users Students"),
    ADMIN_DASHBOARD_USER_STUDENTS_REGISTER("admin/admin-dashboard-users-students-lecturer", "Admin Dashboard Users Students Register"),
    ADMIN_PROFILE("admin/admin-profile", "Admin Profile"),

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
