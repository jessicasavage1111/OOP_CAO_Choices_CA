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

    @Override
    public boolean addStudentCourses(int caoNumber, String courseid, int order) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean success = false;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "INSERT INTO student_courses VALUES (?,?,?)";
            ps = con.prepareStatement(query);

            ps.setInt(1, caoNumber);
            ps.setString(2, courseid);
            ps.setInt(3, order);


            //Using a PreparedStatement to execute SQL - UPDATE...
            success = (ps.executeUpdate() == 1);

        } catch (SQLException e)
        {
            throw new DaoException("addStudentCourses() " + e.getMessage());
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
                throw new DaoException("insertStudentCourses() " + e.getMessage());
            }
        }
        return success;
    }

    @Override
    public boolean updateStudentCourses(int caoNumber, String courseid, int order) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean success = false;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "UPDATE student_courses SET courseid = ? WHERE EXISTS(SELECT * FROM student_courses WHERE caoNumber = ? AND order = ?)";
            ps = con.prepareStatement(query);

            ps.setString(1, courseid);
            ps.setInt(2, caoNumber);
            ps.setInt(3, order);


            //Using a PreparedStatement to execute SQL - UPDATE...
            success = (ps.executeUpdate() == 1);

        } catch (SQLException e)
        {
            throw new DaoException("updateStudentCourses() " + e.getMessage());
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
                throw new DaoException("updateStudentCourses() " + e.getMessage());
            }
        }
        return success;
    }

    @Override
    public StudentCourses findStudentCourses(int caoNumber) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StudentCourses c = null;

        try
        {
            con = this.getConnection();

            String query = "SELECT * FROM student_courses WHERE caoNumber = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, caoNumber);  // search based on the cao number

            rs = ps.executeQuery();
            if (rs.next())
            {
                int cao = rs.getInt("caoNumber");
                String courseId = rs.getString("courseid");
                int order = rs.getInt("order");
                c = new StudentCourses(cao, courseId, order);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findStudentCourses() " + e.getMessage());
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
                throw new DaoException("findStudentCourses() " + e.getMessage());
            }
        }
        return c;     // s may be null
    }
}
