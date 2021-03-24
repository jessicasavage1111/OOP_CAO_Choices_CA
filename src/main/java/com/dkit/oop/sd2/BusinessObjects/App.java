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


import com.dkit.oop.sd2.DTOs.Student;

public class App
{

    public static void main(String[] args)
    {
        StudentManager studentManager = new StudentManager();

        studentManager.addStudent(new Student(12345678, "1999-12-30", "catsNdogs"));


    }
}
