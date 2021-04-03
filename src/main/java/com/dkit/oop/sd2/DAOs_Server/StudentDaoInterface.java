package com.dkit.oop.sd2.DAOs_Server;

import com.dkit.oop.sd2.DTOs_Core.Student;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;

public interface StudentDaoInterface {
    public List<Student> findAllStudents() throws DaoException;

    public Student findStudent(int caoNumber) throws DaoException;

    public boolean registerStudent(Student s) throws DaoException;
}
