package com.dkit.oop.sd2.BusinessObjects;

import com.dkit.oop.sd2.DAOs.MySqlStudentDao;
import com.dkit.oop.sd2.DAOs.StudentDaoInterface;
import com.dkit.oop.sd2.DTOs.Student;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentManager {
    // Store all students in a Map <caoNumber => student>
    private Map<Integer, Student> studentsMap = new HashMap<>();

    public StudentManager() {

        StudentDaoInterface studentDao = new MySqlStudentDao();
        try
        {
            List<Student> studentList = studentDao.findAllStudents();

            // add all students from List to Map
            for( Student student : studentList)
            {
                studentsMap.put(student.getCaoNumber(), student);
            }
            System.out.println(studentsMap);
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }

//        studentsMap.put(22224444, new Student(22224444, "1999-03-15", "Annie$101"));
//        studentsMap.put(50501111, new Student(50501111, "2003-12-25", "banAnna$%"));
//        studentsMap.put(10103333, new Student(10103333, "2002-04-01", "cats&dogs:)"));
//        studentsMap.put(33330000, new Student(33330000, "2000-07-04", "guessMe1984"));

    }

    public Student getStudent(Integer caoNumber) {
        Student student = studentsMap.get(caoNumber); // get a reference to student object (could be null)
        Student clone=null;
        if(student!=null)
            clone = new Student(student);
        return clone;  // return a clone of the student
    }

    public void addStudent(Student student) {
        Integer caoNumber = student.getCaoNumber();
        studentsMap.put(caoNumber, new Student(student));
    }

    public void removeStudent(Integer caoNumber) {
        studentsMap.remove(caoNumber);
    }

    public void addStudent(int i, String s, String catsNdogs) {
    }

//    isRegistered( caoNumber)
//        students.isValid()
}
