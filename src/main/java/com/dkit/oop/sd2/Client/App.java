package com.dkit.oop.sd2.Client;

/**
 * OOP 2021
 * This App demonstrates the use of a Data Access Object (DAO)
 * to separate Business logic from Database specific logic.
 * It uses DAOs, Data Transfer Objects (DTOs), and
 * a DaoInterface to define a contract between Business Objects
 * and DAOs.
 * <p>
 * "Use a Data Access Object (DAO) to abstract and encapsulate all
 * access to the data source. The DAO manages the connection with
 * the data source to obtain and store data" Ref: oracle.com
 * <p>
 * Here we use one DAO per database table.
 * <p>
 * Use the SQL script included with this project to create the
 * required MySQL user_database and user table
 */


import com.dkit.oop.sd2.DTOs_Core.CAOService;
import com.dkit.oop.sd2.DTOs_Core.Colours;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {

    private static Scanner keyboard = new Scanner(System.in);
    private static Socket socket;

    static {
        try {
            socket = new Socket(CAOService.HOSTNAME, 8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Scanner socketReader;

    static {
        try {
            socketReader = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("CAO Online - CA5");
        new App().start();
    }

    private void start() {

        boolean loop = true;
        MainMenu menuOption;
        int option;

        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort());

            System.out.println("Client message: The Client is running and has connected to the server");

            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers


            while (loop) {

                printMainMenu();
                try {
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
                        RegexChecker rc = new RegexChecker();
                        System.out.println("Please enter CAO Number :");
                        int caoNumber = keyboard.nextInt();
                        rc.caoChecker(caoNumber);
                        System.out.println("Please enter Date of Birth in the format of 'year-month-day' :");
                        String dateOfBirth = keyboard.next();
                        rc.dobChecker(dateOfBirth);
                        System.out.println("Please enter Password : ");
                        String password = keyboard.next();
                        rc.passwordChecker(password);

                            String regCommand = CAOService.REGISTER_COMMAND + CAOService.BREAKING_CHARACTER + caoNumber + CAOService.BREAKING_CHARACTER + dateOfBirth + CAOService.BREAKING_CHARACTER + password;
                            socketWriter.println(regCommand);

                            if (regCommand.startsWith(CAOService.REGISTER_COMMAND))   //we expect the server to return a time
                            {
                                String reply = socketReader.nextLine(); //wait for reply from server
                                System.out.println("Client message: Response from server: " + reply);
                            } else                            // the user has entered the Echo command or an invalid command
                            {
                                String input = socketReader.nextLine();
                                System.out.println("Client message: Response from server: \"" + input + "\"");
                            }

                            break;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Selection out of range. Please try again: ");
                    keyboard.nextLine();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Selection out of range. Please try again: ");
                    keyboard.nextLine();
                } catch (NoSuchElementException e) {
                    System.out.println("Selection out of range. Please try again: ");
                    keyboard.nextLine();
                } catch (DaoException throwables) {
                    throwables.printStackTrace();
                }
            }
            socketWriter.close();
            socketReader.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Client message: IOException: " + e);
        }
    }

    private static void printMainMenu() {

        System.out.println("\n Options to select:");
        for (int i = 0; i < MainMenu.values().length; i++) {
            System.out.println("\t" + i + ". " + MainMenu.values()[i].toString());
        }
        System.out.println("Enter a number to select option (enter 0 to cancel):>");
    }

    private static void loginMenu() throws DaoException, IllegalArgumentException {
        RegexChecker rg = new RegexChecker();

        Scanner in = new Scanner(System.in);
        try {
            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers

            System.out.println("Please enter CAO Number :");
            int caoNumber = keyboard.nextInt();
            rg.caoChecker(caoNumber);
            System.out.println("Please enter Date of Birth in the format of 'year-month-day' :");
            String dateOfBirth = keyboard.next();
            rg.dobChecker(dateOfBirth);
            System.out.println("Please enter Password : ");
            String password = keyboard.next();
            rg.passwordChecker(password);

            String loginCommand = CAOService.LOGIN + CAOService.BREAKING_CHARACTER + caoNumber + CAOService.BREAKING_CHARACTER + dateOfBirth + CAOService.BREAKING_CHARACTER + password;
            socketWriter.println(loginCommand);

            if (loginCommand.startsWith(CAOService.LOGIN))   //we expect the server to return a time
            {
                String reply = socketReader.nextLine(); //wait for reply from server
                if (reply.startsWith(CAOService.SUCCESSFUL_LOGIN)) {
                    boolean loop = true;
                    LoginMenu menuOption;
                    int option1;
                    while (loop) {
                        System.out.println("\nOptions:");
                        for (int i = 0; i < LoginMenu.values().length; i++) {
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

                                    String displayCommand = CAOService.DISPLAY_COURSE + CAOService.BREAKING_CHARACTER + courseID;
                                    socketWriter.println(displayCommand);

                                    if (displayCommand.startsWith(CAOService.DISPLAY_COURSE))   //we expect the server to return a time
                                    {
                                        String display = socketReader.nextLine(); //wait for reply from server
                                        if (display.startsWith(CAOService.DISPLAY_FAILED)) {
                                            System.out.println(display);
                                        }
                                        else{
                                            String displayA[] = display.split(CAOService.BREAKING_CHARACTER);
                                            System.out.println(displayA);
                                        }
                                    }
                                    break;
                                case DISPLAY_ALL_COURSES:
                                    String displayAllCommand = CAOService.DISPLAY_ALL;
                                    socketWriter.println(displayAllCommand);

                                    if (displayAllCommand.startsWith(CAOService.DISPLAY_ALL))   //we expect the server to return a time
                                    {
                                        String display = socketReader.nextLine(); //wait for reply from server
                                        if (display.startsWith(CAOService.DISPLAY_ALL_FAILED)) {
                                            System.out.println(display);
                                        }
                                        else{
                                            System.out.println(display);
                                        }
                                    }
                                    break;
                                case DISPLAY_CURRENT_CHOICES:
                                    //System.out.println(studentCourseDao.findStudentCourses(caoNumber));
                                    break;
                                case UPDATE_CHOICES:
                                    ArrayList<String> courseList = new ArrayList<>(10);
                                    for (int i = 1; i <= 10; i++) {
                                        System.out.println("Enter the Course ID for the position " + i + " on your Course Choices list :");
                                        String choice = keyboard.next();
                                        courseList.add(choice);
                                    }
                                    //pass through list of courseID updateStudentCourses (caoNumber, courseList);
                                    //studentCourseDao.updateStudentCourses(caoNumber, courseList);
                                    break;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(Colours.RED + "ArrayIndexOutOfBoundsException, Try again" + Colours.RESET);
                            keyboard.nextLine();
                        } catch (InputMismatchException ime) {
                            System.out.println(Colours.RED + "InputMismatchException, Try again" + Colours.RESET);
                            keyboard.nextLine();
                        }
                    }
                }
            } else                            // the user has entered the Echo command or an invalid command
            {
                String input = socketReader.nextLine();
                System.out.println(input);
            }
            socketWriter.close();
            socketReader.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Client message: IOException: " + e);
        }
    }
}
