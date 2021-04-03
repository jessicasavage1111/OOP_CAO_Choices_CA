package com.dkit.oop.sd2.Client;

/** OOP 2021
 * This App demonstrates the use of a Data Access Object (DAO)
 * to separate Business logic from Database specific logic.
 * It uses DAOs, Data Transfer Objects (DTOs), and
 * a DaoInterface to define a contract between Business Objects
 * and DAOs.
 *
 * "Use a Data Access Object (DAO) to abstract and encapsulate all
 * access to the data source. The DAO manages the connection with
 * the data source to obtain and store data" Ref: oracle.com
 *
 * Here we use one DAO per database table.
 *
 * Use the SQL script included with this project to create the
 * required MySQL user_database and user table
 */


import com.dkit.oop.sd2.DAOs_Server.*;
import com.dkit.oop.sd2.DTOs_Core.Colours;
import com.dkit.oop.sd2.DTOs_Core.Student;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App
{

    private static Scanner keyboard = new Scanner(System.in);

    public static void main( String[] args )
    {
        System.out.println( "CAO Online - CA5" );
        new App() .start();
    }

    private void start() {
        StudentDaoInterface studentDao = new MySqlStudentDao();
        boolean loop= true;
        MainMenu menuOption;
        int option;
        while (loop)
        {
            printMainMenu();
            try
            {
                option = keyboard.nextInt();
                keyboard.nextLine();
                menuOption = MainMenu.values()[option];
                switch (menuOption) {
                    case QUIT:
                        loop = false;
                        break;
                    case LOGIN:
                        loginMenu();
                        break;
                    case REGISTER:
                        System.out.println("Please enter CAO Number :");
                        int caoNumber = keyboard.nextInt();
                        System.out.println("Please enter Date of Birth in the format of 'year-month-day' :");
                        String dateOfBirth = keyboard.next();
                        System.out.println("Please enter Password : ");
                        String password = keyboard.next();
                        Student s = new Student(caoNumber, dateOfBirth, password);
                        studentDao.registerStudent(s);
                        break;
                }
            }
            catch (IllegalArgumentException e){
                System.out.println("Selection out of range. Please try again: ");
                keyboard.nextLine();
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Selection out of range. Please try again: ");
                keyboard.nextLine();
            }
            catch (NoSuchElementException e){
                System.out.println("Selection out of range. Please try again: ");
                keyboard.nextLine();
            } catch (DaoException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private static void printMainMenu(){

        System.out.println("\n Options to select:");
        for(int i = 0; i < MainMenu.values().length; i++){
            System.out.println("\t" +i + ". " + MainMenu.values()[i].toString());
        }
        System.out.println("Enter a number to select option (enter 0 to cancel):>");
    }

    private static void loginMenu() throws DaoException {
        // load students
        StudentDaoInterface studentDao = new MySqlStudentDao();
        CourseDaoInterface courseDao = new MySqlCourseDao();
        StudentCoursesDaoInterface studentCourseDao = new MySqlStudentCoursesDao();



        System.out.println("Please enter CAO Number :");
        int caoNumber = keyboard.nextInt();
        System.out.println("Please enter Date of Birth in the format of 'year-month-day' :");
        String dateOfBirth = keyboard.next();
        System.out.println("Please enter Password : ");
        String password = keyboard.next();

        if(studentDao.findStudent(caoNumber) == null)
        {
            System.out.println(Colours.RED + "CAO Number cannot be found" + Colours.RESET);

        }
        else if (!studentDao.findStudent(caoNumber).getDayOfBirth().equals(dateOfBirth) && !studentDao.findStudent(caoNumber).getPassword().equals(password))
        {
            System.out.println(Colours.RED + "Incorrect Date of Birth and Password" + Colours.RESET);

        }
        else if (!studentDao.findStudent(caoNumber).getPassword().equals(password))
        {
            System.out.println(Colours.RED + "Incorrect Password" + Colours.RESET);

        }
        else if (!studentDao.findStudent(caoNumber).getDayOfBirth().equals(dateOfBirth))
        {
            System.out.println(Colours.RED + "Incorrect Date of Birth" + Colours.RESET);

        }
        else{
            boolean loop = true;
            LoginMenu menuOption;
            int option1;
            while (loop) {
                System.out.println("\nOptions:");
                for (int i = 0; i < LoginMenu.values().length; i++)
                {
                    System.out.println("\t" + Colours.PURPLE + i + ". " + LoginMenu.values()[i].toString() + Colours.RESET);
                }
                System.out.println("Select an option (0 to quit): ");//Don't allow duplicates, ID values unique
                try {
                    option1 = keyboard.nextInt();
                    keyboard.nextLine();
                    menuOption = LoginMenu.values()[option1];
                    switch (menuOption) {
                        case LOGOUT:
                            loop = false;
                            break;
                        case DISPLAY_A_COURSE:
                            System.out.println("Enter Course ID: ");
                            String courseID = keyboard.next();
                            System.out.println(courseDao.findCourse(courseID));
                            break;
                        case DISPLAY_ALL_COURSES:
                            System.out.println(courseDao.findAllCourses());
                            break;
                        case DISPLAY_CURRENT_CHOICES:
                            System.out.println(studentCourseDao.findStudentCourses(caoNumber));
                            break;
                        case ADD_CHOICES:
                            System.out.println("Enter Course ID: ");
                            String aCourseID = keyboard.next();
                            System.out.println("Enter Position of Course, eg between 1-10");
                            int order = keyboard.nextInt();
                            studentCourseDao.addStudentCourses(caoNumber, aCourseID, order);
                            break;
                        case UPDATE_CHOICES:
                            System.out.println("Enter Position you want to change");
                            int change = keyboard.nextInt();
                            System.out.println("Enter new course ID");
                            String nCourseID = keyboard.next();
                            studentCourseDao.updateStudentCourses(caoNumber, nCourseID, change);
                            break;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(Colours.RED + "IllegalArgumentException, Try again" + Colours.RESET);
                    keyboard.nextLine();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(Colours.RED + "ArrayIndexOutOfBoundsException, Try again" + Colours.RESET);
                    keyboard.nextLine();
                } catch (InputMismatchException ime) {
                    System.out.println(Colours.RED + "InputMismatchException, Try again" + Colours.RESET);
                    keyboard.nextLine();
                } catch (DaoException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
