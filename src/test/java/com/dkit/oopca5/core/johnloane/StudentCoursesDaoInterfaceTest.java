package com.dkit.oopca5.core.johnloane;

import com.dkit.oop.sd2.DAOs_Server.MySqlStudentCoursesDao;
import com.dkit.oop.sd2.DAOs_Server.StudentCoursesDaoInterface;
import com.dkit.oop.sd2.DTOs_Core.StudentCourses;
import com.dkit.oop.sd2.Exceptions.DaoException;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

import static org.junit.Assert.assertNotNull;

public class StudentCoursesDaoInterfaceTest {
    @Test
    public void testFindAllStudentCourses() throws DaoException {

        StudentCoursesDaoInterface studentCoursesDao = new MySqlStudentCoursesDao();
        List<StudentCourses> studentCoursesList = studentCoursesDao.findAllStudentCourses();
        int count = 0;
        for(int i=0; i<studentCoursesList.size(); i++)
        {
            count++;
        }
        int actual = count;
        int expected = studentCoursesList.size();
        assertEquals(expected,actual);
        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);
    }

    @Test
    public void testAddStudentCourses() throws DaoException {

        StudentCoursesDaoInterface studentCourseDao = new MySqlStudentCoursesDao();
        int cao = 12345673;
        String course = "DB509";
        int order = 1;

        StudentCourses expected = new StudentCourses(cao, course, order);
        studentCourseDao.addStudentCourses(cao, course, order);
        StudentCourses actual = studentCourseDao.findStudentCourses(cao);

        assertEquals(expected,actual);
        System.out.println("actual: " + actual);
        System.out.println("expected: " + expected);

    }

    @Test
    public void testFindStudentCourses() throws DaoException {
        StudentCoursesDaoInterface studentCourseDao = new MySqlStudentCoursesDao();
        int cao = 12345673;

        StudentCourses actual = studentCourseDao.findStudentCourses(cao);
        assertNotNull(actual);
    }

    @Test
    //CAN'T GET UPDATE STUDENT WORKING
    public void testUpdateStudentCourses() throws DaoException {
        StudentCoursesDaoInterface studentCourseDao = new MySqlStudentCoursesDao();
        int cao = 12345673;
        String course = "DB509";
        int order = 3;

        boolean expected = studentCourseDao.updateStudentCourses(cao,course,order);
        assertTrue(expected);
    }

}
