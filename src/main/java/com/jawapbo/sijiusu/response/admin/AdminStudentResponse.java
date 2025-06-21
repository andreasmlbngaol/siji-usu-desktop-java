package com.jawapbo.sijiusu.response.admin;

public record AdminStudentResponse(
    Long id,
    String name,
    String email,
    String nim,
    String faculty,
    String major
//    List<CourseTaughtResponse> coursesTaught
) {}
