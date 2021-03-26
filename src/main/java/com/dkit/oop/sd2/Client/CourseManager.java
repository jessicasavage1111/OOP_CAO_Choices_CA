package com.dkit.oop.sd2.Client;

import com.dkit.oop.sd2.DAOs_Server.MySqlCourseDao;
import com.dkit.oop.sd2.DAOs_Server.CourseDaoInterface;
import com.dkit.oop.sd2.DTOs_Core.Colours;
import com.dkit.oop.sd2.DTOs_Core.Course;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseManager {

    private Map<String, Course> coursesMap = new HashMap<>();

    public CourseManager() {

        CourseDaoInterface courseDao = new MySqlCourseDao();
        try
        {
            List<Course> courseList = courseDao.findAllCourses();

            // add all students from List to Map
            for( Course course : courseList)
            {
                coursesMap.put(course.getCourseId(), course);
            }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }

        // Hardcode some values to get started
//        coursesMap.put("DK820", new Course("DK820", "8","BSc in Software Developmnet", "Dundalk IT"));
//        coursesMap.put("DK722", new Course("DK722", "7","BSc in Dev Ops", "Dundalk IT"));
//        coursesMap.put("DN150", new Course("DN150", "8","BA Engineering", "University College Dublin"));
//        coursesMap.put("TU602", new Course("TU602", "8","BSc Computer Science","TU Dublin"));
    }

    public static void CourseMenu()
    {
        System.out.println("\nOptions:");
        for (int i = 0; i < LoginMenu.values().length; i++)
        {
            System.out.println("\t" + Colours.PURPLE + i + ". " + LoginMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.println("Select an option (0 to quit): ");//Don't allow duplicates, ID values unique
    }

    public Course getCourse( String courseId ) {
        Course course = coursesMap.get(courseId);  // get course using id and then
        return new Course( course ); //  return a clone (make a copy) of the course.
    }

    // Returns a *List* of Courses.
    // Courses are not in any particular order.
    //
    public List<Course> getAllCourses() {

        ArrayList<Course> list = new ArrayList<>(); // new ArrayList to store copy of Map data

        // Iterate through all values in the courseMap and add
        // a copy (a clone) of each course to the new List
        for (Map.Entry<String, Course> entry : coursesMap.entrySet()) {
            Course course = entry.getValue();   // get course from map entry
            list.add(new Course(course));       // add course clone to the List
        }
        return list;    // return the List
    }

    // add a Course object to the courses store
    //
    public void addCourse(Course course) {
        // clone the course object to prevent external access
        String courseId = course.getCourseId();     // get the course id from the object
        coursesMap.put( courseId, new Course(course));    // create and store a clone of the course object
    }

    // remove a course, given a courseId
    //
    public void removeCourse(String courseId) {
        coursesMap.remove(courseId);
    }

    // editCourse(courseId);       // not required for this iteration
}
