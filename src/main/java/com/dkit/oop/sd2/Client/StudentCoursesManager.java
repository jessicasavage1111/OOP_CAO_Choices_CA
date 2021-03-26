package com.dkit.oop.sd2.Client;

import com.dkit.oop.sd2.DAOs_Server.MySqlStudentCoursesDao;
import com.dkit.oop.sd2.DAOs_Server.StudentCoursesDaoInterface;
import com.dkit.oop.sd2.DTOs_Core.StudentCourses;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.HashMap;
import java.util.List;

public class StudentCoursesManager {

    HashMap<Integer, StudentCourses> selectedChoices;

    public StudentCoursesManager() {

        StudentCoursesDaoInterface courseDao = new MySqlStudentCoursesDao();
        try
        {
            List<StudentCourses> choices = courseDao.findAllStudentCourses();

            // add all students from List to Map
            for( StudentCourses choice : choices)
            {
                selectedChoices.put(choice.getCaoNumber(), choice);
            }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    public void updateChoices(int caoNumber, List<String> choices) {

    }


}
