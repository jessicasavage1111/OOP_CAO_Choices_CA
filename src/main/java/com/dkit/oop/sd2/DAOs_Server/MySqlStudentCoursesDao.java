package com.dkit.oop.sd2.DAOs_Server;

import com.dkit.oop.sd2.DTOs_Core.StudentCourses;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlStudentCoursesDao extends MySqlDao implements StudentCoursesDaoInterface
{

    @Override
    public List<StudentCourses> findAllStudentCourses() throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<StudentCourses> choices = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM student_courses";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                int caoNumber = rs.getInt("caoNumber");
                String courseId = rs.getString("courseid");
                int order = rs.getInt("order");
                StudentCourses c = new StudentCourses(caoNumber, courseId, order);
                choices.add(c);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllStudentCourses() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllStudentCourses() " + e.getMessage());
            }
        }
        return choices;     // may be empty
    }

}
