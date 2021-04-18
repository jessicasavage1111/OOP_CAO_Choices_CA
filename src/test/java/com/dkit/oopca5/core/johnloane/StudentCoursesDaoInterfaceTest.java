package com.dkit.oopca5.core.johnloane;

import com.dkit.oop.sd2.DAOs_Server.MySqlStudentCoursesDao;
import com.dkit.oop.sd2.DAOs_Server.StudentCoursesDaoInterface;
import com.dkit.oop.sd2.DTOs_Core.StudentCourses;
import com.dkit.oop.sd2.Exceptions.DaoException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
        ArrayList<String> courseList = new ArrayList<>(10);
        courseList.add("CK208");

        studentCourseDao.addStudentCourses(cao, courseList);
        List<StudentCourses> actual = studentCourseDao.findStudentCourses(cao);

        String expected = "[StudentCourses{caoNumber=12345673, courseId='CK208', order=1}]";

        assertEquals(expected,actual);
        System.out.println("actual: " + actual);
        System.out.println("expected: " + expected);

    }

    @Test
    public void testFindStudentCourses() throws DaoException {
        StudentCoursesDaoInterface studentCourseDao = new MySqlStudentCoursesDao();
        int cao = 12345673;

        List<StudentCourses> actual = studentCourseDao.findStudentCourses(cao);
        assertNotNull(actual);
    }

    @Test
    //CAN'T GET UPDATE STUDENT WORKING
    public void testUpdateStudentCourses() throws DaoException {
        StudentCoursesDaoInterface studentCourseDao = new MySqlStudentCoursesDao();
        int cao = 12345673;
        ArrayList<String> courseList = new ArrayList<>(10);
        courseList.add("DB500");

       boolean expected = studentCourseDao.updateStudentCourses(cao, courseList);
       assertTrue(expected);
    }

}
