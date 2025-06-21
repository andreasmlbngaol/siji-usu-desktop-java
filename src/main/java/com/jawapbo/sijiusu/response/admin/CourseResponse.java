package com.jawapbo.sijiusu.response.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CourseResponse(
    Long id,
    String name,
    @JsonProperty("course_sections") List<SectionResponse> sections
) { }

