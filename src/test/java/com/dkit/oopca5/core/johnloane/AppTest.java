package com.dkit.oopca5.core.johnloane;

import com.dkit.oop.sd2.Client.StudentManager;
import com.dkit.oop.sd2.DAOs_Server.MySqlStudentDao;
import com.dkit.oop.sd2.DAOs_Server.StudentDaoInterface;
import com.dkit.oop.sd2.DTOs_Core.Student;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testAddStudentToStudentManager(){
        StudentManager sm = new StudentManager();

        int cao = 22223333;
        String dob = "1999-11-26";
        String pw = "w3e4r5";
        Student expected = new Student(cao, dob, pw);
        sm.addStudent(expected);

        Student actual = sm.getStudent(22223333);
        assertTrue(expected.equals(actual));
    }

    @Test
    public void testStudentDao(){
        StudentDaoInterface studentDao = new MySqlStudentDao();
    }
}
