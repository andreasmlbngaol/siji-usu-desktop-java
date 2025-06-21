package com.jawapbo.sijiusu.response.student;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CoursesTakenResponse(
    Long id,
    @JsonProperty("course_name") String courseName,
    @JsonProperty("section_name") String sectionName,
    String room,
    String lecturer
) {}
