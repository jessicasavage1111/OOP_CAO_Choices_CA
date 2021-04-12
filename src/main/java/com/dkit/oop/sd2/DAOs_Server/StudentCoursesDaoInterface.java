package com.dkit.oop.sd2.DAOs_Server;

import com.dkit.oop.sd2.DTOs_Core.StudentCourses;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;

public interface StudentCoursesDaoInterface {

    public List<StudentCourses> findAllStudentCourses() throws DaoException;

    public boolean addStudentCourses(StudentCourses c) throws DaoException;

    public StudentCourses findStudentCourses(int caoNumber) throws DaoException;

    public boolean updateStudentCourses(StudentCourses c) throws DaoException;
}
