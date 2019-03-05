package com.johan_apps.exelsa.model;

public class Course {
    private String id;
    private String courseName;
    private String teacher;
    private String day;
    private String timeStart;
    private String timeEnd;
    private int sks;

    public Course() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
