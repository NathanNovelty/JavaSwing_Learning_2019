/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmanagersql;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NhanN
 */
public class BookDataDAO implements Serializable{
    
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }
    
    private List<BookDataDTO> listData;
    public List<BookDataDTO> getListData(){
        return listData;
    }
    
    
    //Get All Data
    public List<BookDataDTO> getAllData() throws SQLException{
        try {
            con = MyConnection.myConnection();
            if (con != null) {
                String sql = "SELECT * FROM BookInfo";
                stm = con.prepareStatement(sql);
                rs =stm.executeQuery();
                while (rs.next()) {                    
                    String bookCode = rs.getString("BookCode");
                    String bookName = rs.getString("BookName");
                    String author = rs.getString("Author");
                    String publisher = rs.getString("Publisher");
                    int publishYear = rs.getInt("PublishYear");
                    boolean isRent = rs.getBoolean("IsRent");
                    BookDataDTO dto = new BookDataDTO(bookCode, bookName, author, publisher, publishYear, isRent);
                    if (this.listData == null) {
                        this.listData = new ArrayList<>();
                    }
                    listData.add(dto);
                }
                return listData;
            }
        } finally{
            closeConnection();
        }
        return null;
    }
}
