package com.dkit.oop.sd2.DAOs_Server;

import com.dkit.oop.sd2.DTOs_Core.Student;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlStudentDao extends MySqlDao implements StudentDaoInterface {

    @Override
    public List<Student> findAllStudents() throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM STUDENT";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                int caoNumber = rs.getInt("caoNumber");
                String dob = rs.getString("dob");
                String password = rs.getString("password");
                Student s = new Student(caoNumber, dob, password);
                students.add(s);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllStudents() " + e.getMessage());
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
                throw new DaoException("findAllStudents() " + e.getMessage());
            }
        }
        return students;     // may be empty
    }

    @Override
    public boolean registerStudent(Student s) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean success = false;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "INSERT INTO STUDENT VALUES (?,?,?)";
            ps = con.prepareStatement(query);

            ps.setInt(1, s.getCaoNumber());
            ps.setString(2, s.getDayOfBirth());
            ps.setString(3, s.getPassword());


            //Using a PreparedStatement to execute SQL - UPDATE...
            success = (ps.executeUpdate() == 1);

        } catch (SQLException e)
        {
            throw new DaoException("insertStudent() " + e.getMessage());
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
                throw new DaoException("insertStudent() " + e.getMessage());
            }
        }
        return success;
    }

    @Override
    public Student findStudent(int caoNumber) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student s = null;

        try
        {
            con = this.getConnection();

            String query = "SELECT * FROM Student WHERE caoNumber = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, caoNumber);  // search based on the cao number

            rs = ps.executeQuery();
            if (rs.next())
            {
                caoNumber = rs.getInt("CAONUMBER");
                String dateOfBirth = rs.getString("dob");
                String password = rs.getString("PASSWORD");

                s = new Student(caoNumber, dateOfBirth, password);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findStudent() " + e.getMessage());
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
                throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
            }
        }
        return s;     // s may be null
    }
}
