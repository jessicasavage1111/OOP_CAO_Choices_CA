package com.dkit.oopca5.core.johnloane;

import com.dkit.oop.sd2.DAOs_Server.MySqlStudentDao;
import com.dkit.oop.sd2.DAOs_Server.StudentDaoInterface;
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
    public void testStudentDao(){
        StudentDaoInterface studentDao = new MySqlStudentDao();
    }
}
