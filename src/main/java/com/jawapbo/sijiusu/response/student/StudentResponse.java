package com.jawapbo.sijiusu.response.student;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record StudentResponse(
    Long id,
    String name,
    String email,
    String nim,
    String faculty,
    @JsonProperty("courses_taken") List<CoursesTakenResponse> coursesTaken
) { }

