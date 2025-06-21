package com.jawapbo.sijiusu.response.admin;

import java.util.List;

public record AdminLecturerResponse(
    Long id,
    String name,
    String email,
    String nip,
    String nidn,
    String faculty,
    String department,
    List<CourseTaughtResponse> coursesTaught
) { }
