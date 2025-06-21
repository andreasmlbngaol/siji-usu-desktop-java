package com.jawapbo.sijiusu.response.admin;

import java.util.List;

public record FacultyResponse(
    Long id,
    String name,
    String code,
    List<FacultyDepartmentResponse> departments
) {
    @Override
    public String toString() {
        return name;
    }
}