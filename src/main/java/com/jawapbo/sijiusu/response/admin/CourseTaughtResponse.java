package com.jawapbo.sijiusu.response.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CourseTaughtResponse(
    Long id,
    @JsonProperty("course_name") String courseName,
    @JsonProperty("section_name") String sectionName,
    String room,
    String lecturer
) {}