package com.dkit.oop.sd2.DTOs_Core;

import java.util.Objects;

public class StudentCourses {

    private int caoNumber;
    private String courseId;
    private int order;

    public StudentCourses(int caoNumber, String courseId, int order) {
        this.caoNumber = caoNumber;
        this.courseId = courseId;
        this.order = order;
    }

    public int getCaoNumber() {
        return caoNumber;
    }

    public void setCaoNumber(int caoNumber) {
        this.caoNumber = caoNumber;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "StudentCourses{" +
                "caoNumber=" + caoNumber +
                ", courseId='" + courseId + '\'' +
                ", order=" + order +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourses that = (StudentCourses) o;
        return caoNumber == that.caoNumber &&
                order == that.order &&
                courseId.equals(that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caoNumber, courseId, order);
    }
}
