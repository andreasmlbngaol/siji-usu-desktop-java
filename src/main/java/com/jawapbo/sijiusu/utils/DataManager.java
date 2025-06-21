package com.jawapbo.sijiusu.utils;

public class DataManager {
    private static Long facultyId;
    private static Long majorId;
    private static Long roomId;
    private static Long courseId;
    private static Long sectionId;

    public static Long getFacultyId() {
        return facultyId;
    }

    public static void setFacultyId(Long facultyId) {
        DataManager.facultyId = facultyId;
    }

    public static Long getMajorId() {
        return majorId;
    }

    public static void setMajorId(Long majorId) {
        DataManager.majorId = majorId;
    }

    public static Long getRoomId() {
        return roomId;
    }

    public static void setRoomId(Long roomId) {
        DataManager.roomId = roomId;
    }

    public static Long getCourseId() {
        return courseId;
    }

    public static void setCourseId(Long courseId) {
        DataManager.courseId = courseId;
    }

    public static Long getSectionId() {
        return sectionId;
    }

    public static void setSectionId(Long sectionId) {
        DataManager.sectionId = sectionId;
    }
}
