/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhannt.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author NhanN
 */
public class MyConnection {

    public static Connection getConnection() {
        try {
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursejdbc", "root", "trungnhan137");
            return con;
        } catch (SQLException e) {
            Logger.getLogger("Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            Logger.getLogger("Error: " + e.getMessage());
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }
}
