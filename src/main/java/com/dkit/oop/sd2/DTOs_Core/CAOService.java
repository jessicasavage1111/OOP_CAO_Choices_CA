package com.dkit.oop.sd2.DTOs_Core;

/* The CAOService class has constants to define all of the messages that are sent between the Client and Server
 */

public class CAOService
{
    public static final int PORT_NUM = 50025;
    public static final String HOSTNAME = "localhost";

    public static final String BREAKING_CHARACTER = "%%";

    public static final String REGISTER_COMMAND = "REGISTER";
    public static final String SUCCESSFUL_REGISTER = "REGISTERED";
    public static final String FAILED_REGISTER = "REG FAILED";

    public static final String LOGIN = "LOGIN";
    public static final String SUCCESSFUL_LOGIN = "LOGGED IN";
    public static final String FAILED_LOGIN = "LOGIN FAILED";

    public static final String LOGOUT = "LOGOUT";

    public static final String DISPLAY_COURSE = "DISPLAY COURSE";
    public static final String DISPLAY_ALL = "DISPLAY ALL";

    public static final String DISPLAY_CURRENT = "DISPLAY CURRENT";

    public static final String ADD_CHOICES = "ADD";
    public static final String SUCCESSFUL_ADD = "ADDED";
    public static final String FAILED_ADD = "ADD FAILED";

    public static final String UPDATE_CHOICES = "UPDATE";
    public static final String SUCCESSFUL_UPDATE = "UPDATED";
    public static final String FAILED_UPDATE = "UPDATE FAILED";
}
