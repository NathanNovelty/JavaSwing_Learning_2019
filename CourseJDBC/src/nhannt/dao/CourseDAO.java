/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhannt.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nhannt.connection.MyConnection;
import nhannt.dto.CourseDTO;

/**
 *
 * @author NhanN
 */
public class CourseDAO implements Serializable {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public void closeConnection() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (stm != null) {
            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private List<CourseDTO> listCourse;

    public List<CourseDTO> getListCourse() {
        return listCourse;
    }

    public List<CourseDTO> getAllCourse() {

        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "select Code,Name,Credit from coursejdbc.course";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String course = rs.getString("Code");
                    String name = rs.getString("Name");
                    int credit = rs.getInt("Credit");

                    CourseDTO dto = new CourseDTO(course, name, credit);
                    if (listCourse == null) {
                        listCourse = new ArrayList<>();
                    }
                    listCourse.add(dto);
                }
                return listCourse;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }

        return null;
    }

    public boolean addNewCourse(String code, String name, int credit) {
        try {
            con = MyConnection.getConnection();
            con.setAutoCommit(false);
            if (con != null) {
                String sql = "Select Code FROM course WHERE code = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, code);
                rs = stm.executeQuery();
                if (!rs.next()) {
                    String query = "INSERT INTO coursejdbc(Code, Name, Credit) VALUES(?,?,?)";

//                    stm = con.prepareStatement(query);
//                    stm = con.setAutoCommit(false);
                    stm.setString(1, code);
                    stm.setString(2, name);
                    stm.setInt(3, credit);
                    con.commit();
//                    int row = stm.executeUpdate();
//                    if (row > 0) {
//                        return true;
//                    }
                }
                return false;
            }
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public List<CourseDTO> searchNewCourse(String code) {
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "SELECT Code, Name, Credit FROM course WHERE Code = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, code);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String coCode = rs.getString("Code");
                    String coName = rs.getString("Name");
                    int credit = rs.getInt("Credit");
                    CourseDTO dto = new CourseDTO(coCode, coName, credit);
                    if (listCourse == null) {
                        listCourse = new ArrayList<>();
                    }
                    listCourse.add(dto);
                }
                return listCourse;
            }
        } catch (Exception e) {
        }

        return null;
    }
}
