package com.dkit.oop.sd2.BusinessObjects;

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


import com.dkit.oop.sd2.DTOs.Colours;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App
{

    private static Scanner keyboard = new Scanner(System.in);

    public static void main( String[] args )
    {
        System.out.println( "CAO Online - CA4" );
        new App() .start();
    }

    private void start() {

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
                        adminMenu();
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

    private static void loginMenu(){
        // load students
        StudentManager studentManager = new StudentManager();

        // load courses
        CourseManager courseManager= new CourseManager();

        CourseChoicesManager mgr = new CourseChoicesManager(studentManager, courseManager);
//        mgr.readChoicesFile();
//        mgr.saveChoicesToFile();

        System.out.println("Please enter CAO Number :");
        int caoNumber = keyboard.nextInt();
        System.out.println("Please enter Date of Birth in the format of 'year-month-day' :");
        String dateOfBirth = keyboard.next();
        System.out.println("Please enter Password : ");
        String password = keyboard.next();

        if(mgr.login(caoNumber, dateOfBirth, password) == true)
        {
            boolean loop = true;
            LoginMenu menuOption;
            int option1;
            while (loop) {
                studentManager.StudentMenu();
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
                            System.out.println(mgr.getCourseDetails(courseID));
                            break;
                        case DISPLAY_ALL_COURSES:
                            System.out.println(courseManager.getAllCourses());
                            break;
                        case DISPLAY_CURRENT_CHOICES:
                            mgr.getStudentChoices(caoNumber);
                            break;
                        case UPDATE_CHOICES:
                            ArrayList<String> choices = new ArrayList<>();
                            choices.add(String.valueOf(caoNumber));
                            for(int i = 1; i <= 10; i++){
                                System.out.println("Enter the Course ID for the position " + i + " on your Course Choices list :");
                                String choice = keyboard.next();
                                choices.add(choice);
                            }
                            mgr.updateChoices(caoNumber, choices);
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
                }
            }
        }
    }
}
