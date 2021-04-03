package com.dkit.oopca5.core.johnloane;

import com.dkit.oop.sd2.DAOs_Server.MySqlStudentDao;
import com.dkit.oop.sd2.DAOs_Server.StudentDaoInterface;
import com.dkit.oop.sd2.DTOs_Core.Student;
import com.dkit.oop.sd2.Exceptions.DaoException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StudentDaoInterfaceTest {
    @Test
    public void testFindAllStudents() throws DaoException {

        StudentDaoInterface studentDao = new MySqlStudentDao();
        List<Student> studentList = studentDao.findAllStudents();
        int count = 0;
        for(int i=0; i<studentList.size(); i++)
        {
            count++;
        }
        int actual = count;
        int expected = studentList.size();
        assertEquals(expected,actual);
    }
    @Test
    public void testFindStudent() throws DaoException {
        StudentDaoInterface studentDao = new MySqlStudentDao();
        int cao = 12345673;
        Student s = new Student(cao, "2001-08-03", "Password");
        studentDao.registerStudent(s);
        Student actual = studentDao.findStudent(cao);
        assertNotNull(actual);
    }
    @Test
    public void testRegisterStudent() throws DaoException {
        StudentDaoInterface studentDao = new MySqlStudentDao();
        Student s = new Student(11112222, "2001-05-03", "Password");
        studentDao.registerStudent(s);
        Student actual = studentDao.findStudent(11112222);
        assertNotNull(actual);

    }
}
