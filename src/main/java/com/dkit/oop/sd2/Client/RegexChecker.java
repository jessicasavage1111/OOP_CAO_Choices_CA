package com.dkit.oop.sd2.Client;

/* This class should contain static methods to verify input in the application
 */

import com.dkit.oop.sd2.DTOs_Core.Colours;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class RegexChecker
{
    Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {


    }


    public void caoChecker(int cao)
    {
        String caoRegex= "^[0-9]{8}$";
        Pattern caoPattern = Pattern.compile(caoRegex);
        Matcher caoMatcher = caoPattern.matcher(Integer.toString(cao));
        while(!caoMatcher.matches())
        {
            System.out.println(Colours.RED + "ERROR - Please enter cao Number : " + Colours.RESET);
            cao = keyboard.nextInt();
            caoMatcher = caoPattern.matcher(Integer.toString(cao));
        }
    }

    public void passwordChecker(String password)
    {
        String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        while(!passwordMatcher.matches())
        {
            System.out.println(Colours.RED + "ERROR - Please enter Password : " + Colours.RESET);
            password = keyboard.next();
            passwordMatcher = passwordPattern.matcher(password);
        }
    }

    public void dobChecker(String dob)
    {
        String dobRegex= "^\\d{4}[\\-\\/\\s]?((((0[13578])|(1[02]))[\\-\\/\\s]?(([0-2][0-9])|(3[01])))|(((0[469])|(11))[\\-\\/\\s]?(([0-2][0-9])|(30)))|(02[\\-\\/\\s]?[0-2][0-9]))$";
        Pattern dobPattern = Pattern.compile(dobRegex);
        Matcher dobMatcher = dobPattern.matcher(dob);
        while(!dobMatcher.matches())
        {
            System.out.println(Colours.RED + "ERROR - Please enter Date of Birth in the format of 'year-month-day' :" + Colours.RESET);
            dob = keyboard.next();
            dobMatcher = dobPattern.matcher(dob);
        }
    }
}
