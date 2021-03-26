package com.dkit.oop.sd2.Client;

import com.dkit.oop.sd2.DAOs_Server.MySqlStudentDao;
import com.dkit.oop.sd2.DAOs_Server.StudentDaoInterface;
import com.dkit.oop.sd2.DTOs_Core.Colours;
import com.dkit.oop.sd2.DTOs_Core.Student;
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
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    public static void StudentMenu()
    {
        System.out.println("\nOptions:");
        for (int i = 0; i < LoginMenu.values().length; i++)
        {
            System.out.println("\t" + Colours.PURPLE + i + ". " + LoginMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.println("Select an option (0 to quit): ");//Don't allow duplicates, ID values unique
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

    boolean login(int caoNumber, String dateOfBirth, String password) {
        if(getStudent(caoNumber) == null)
        {
            System.out.println(Colours.RED + "CAO Number cannot be found" + Colours.RESET);
            return false;
        }
        else if (!getStudent(caoNumber).getPassword().equals(password))
        {
            System.out.println(Colours.RED + "Incorrect Password" + Colours.RESET);
            return false;
        }
        else if (!getStudent(caoNumber).getDayOfBirth().equals(dateOfBirth))
        {
            System.out.println(Colours.RED + "Incorrect Date of Birth" + Colours.RESET);
            return false;
        }
        else if (!getStudent(caoNumber).getDayOfBirth().equals(dateOfBirth) && !getStudent(caoNumber).getPassword().equals(password))
        {
            System.out.println(Colours.RED + "Incorrect Date of Birth and Password" + Colours.RESET);
            return false;
        }
        else{
            return true;
        }
    }

    public void register(Student student) {
        addStudent(student);
    }

//    isRegistered( caoNumber)
//        students.isValid()
}
