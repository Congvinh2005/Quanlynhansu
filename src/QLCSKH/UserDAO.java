/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCSKH;

/**
 *
 * @author AS
 */
import java.sql.*;

public class UserDAO {
    public User checkLogin(String user, String pass) {
        try {
            Connection cons = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND is_active = 1";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setRole(rs.getString("role"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
