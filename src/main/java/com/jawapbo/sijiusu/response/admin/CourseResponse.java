package com.jawapbo.sijiusu.response.admin;

import java.util.List;

public record CourseResponse(
    Long id,
    String name,
    List<SectionResponse> sections
) { }

