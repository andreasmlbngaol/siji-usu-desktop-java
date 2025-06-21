package com.jawapbo.sijiusu.response.lecturer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jawapbo.sijiusu.response.student.CoursesTakenResponse;

import java.util.List;

public record LecturerResponse(
    Long id,
    String name,
    String email,
    String nip,
    String nidn,
    String department,
    @JsonProperty("courses_taught") List<CoursesTakenResponse> coursesTaught
) { }
