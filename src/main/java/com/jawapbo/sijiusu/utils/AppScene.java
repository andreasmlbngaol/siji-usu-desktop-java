package com.jawapbo.sijiusu.utils;

public enum AppScene {
    LOGIN("login-view", "Login Scene"),
    ADMIN_DASHBOARD("admin/admin-dashboard", "Admin Dashboard"),
    ADMIN_DASHBOARD_ACADEMIC_MAJOR("admin/admin-dashboard-academic-major", "Admin Dashboard Academic"),
    ADMIN_DASHBOARD_ACADEMIC_MAJOR_ID_COURSES("admin/admin-dashboard-academic-major-id", "Admin Dashboard Academic"),
    ADMIN_DASHBOARD_ACADEMIC_MAJOR_ID_COURSE_CREATE("admin/admin-dashboard-academic-major-id-courses-create", "Admin Dashboard Academic"),
    ADMIN_DASHBOARD_ACADEMIC_MAJOR_ID_COURSE_SECTION("admin/admin-dashboard-academic-major-id-courses-section", "Admin Dashboard Academic"),
    ADMIN_DASHBOARD_ACADEMIC_MAJOR_ID_COURSE_SECTION_CREATE("admin/admin-dashboard-academic-major-id-courses-section-create", "Admin Dashboard Academic"),
    ADMIN_DASHBOARD_ACADEMIC_MAJOR_ID_COURSE_SECTION_EDIT("admin/admin-dashboard-academic-major-id-courses-section-edit", "Admin Dashboard Academic"),
    ADMIN_DASHBOARD_ACADEMIC_MAJOR_ID_EDIT("admin/admin-dashboard-academic-major-id-edit", "Admin Dashboard Academic"),
    ADMIN_DASHBOARD_USER("admin/admin-dashboard-users", "Admin Dashboard Users"),
    ADMIN_DASHBOARD_USER_LECTURER("admin/admin-dashboard-users-lecturer", "Admin Dashboard Users Lecturer"),
    ADMIN_DASHBOARD_USER_LECTURER_REGISTER("admin/admin-dashboard-users-lecturer-register", "Admin Dashboard Users Lecturer Register"),
    ADMIN_DASHBOARD_USER_STUDENTS("admin/admin-dashboard-users-students", "Admin Dashboard Users Students"),
    ADMIN_DASHBOARD_USER_STUDENTS_REGISTER("admin/admin-dashboard-users-students-register", "Admin Dashboard Users Students Register"),
    ADMIN_PROFILE("admin/admin-profile", "Admin Profile"),

    STUDENT_DASHBOARD("students/students-dashboard", "Student Dashboard"),
    STUDENT_PROFILE("students/student-profile", "Student Profile"),
    STUDENT_COURSE_SECTION("students/student-course-section", "Student Course Section"),

    LECTURER_DASHBOARD("lecturers/lecturers-dashboard", "Lecturer Dashboard"),
    LECTURER_PROFILE("lecturers/lecturers-profile", "Lecturer Profile"),
    LECTURER_SECTION("lecturers/lecturers-section", "Lecturer Section")
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
