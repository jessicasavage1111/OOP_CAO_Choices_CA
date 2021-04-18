package com.dkit.oopca5.core.johnloane;

import com.dkit.oop.sd2.DAOs_Server.CourseDaoInterface;
import com.dkit.oop.sd2.DAOs_Server.MySqlCourseDao;
import com.dkit.oop.sd2.DTOs_Core.Course;
import com.dkit.oop.sd2.Exceptions.DaoException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CourseDaoInterfaceTest {
    @Test
    public void testFindAllCourses() throws DaoException {
        CourseDaoInterface coursesDao = new MySqlCourseDao();
        List<Course> coursesList = coursesDao.findAllCourses();
        int count = 0;
        for(int i=0; i<coursesList.size(); i++)
        {
            count++;
        }
        int actual = count;
        int expected = coursesList.size();
        assertEquals(expected,actual);
        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);
    }

    @Test
    public void testFindCourse() throws DaoException {
        CourseDaoInterface courseDao = new MySqlCourseDao();
        Course actual = courseDao.findCourse("CK208");
        assertNotNull(actual);
    }
}
