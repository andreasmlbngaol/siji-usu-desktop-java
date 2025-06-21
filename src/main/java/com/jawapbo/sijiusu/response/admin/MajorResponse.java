package com.jawapbo.sijiusu.response.admin;

public record MajorResponse(
    Long id,
    String name,
    String code,
    MajorFacultyResponse faculty
) {}

