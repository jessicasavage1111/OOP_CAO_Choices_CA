package com.dkit.oop.sd2.DAOs_Server;

import com.dkit.oop.sd2.DTOs_Core.Course;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;

public interface CourseDaoInterface {
    public List<Course> findAllCourses() throws DaoException;

    public Course findCourse(String courseId) throws DaoException;
}
