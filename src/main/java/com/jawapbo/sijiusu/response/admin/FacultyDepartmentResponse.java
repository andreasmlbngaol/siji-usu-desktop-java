package com.jawapbo.sijiusu.response.admin;

public record FacultyDepartmentResponse(
    Long id,
    String name,
    String code
) {
    @Override
    public String toString() {
        return name;
    }
}
