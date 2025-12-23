/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCSKH;

/**
 *
 * @author AS
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection getConnection() {
        Connection cons = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Thay đổi user/pass cho đúng máy bạn
            cons = DriverManager.getConnection("jdbc:mysql://localhost:3306/cskh_system", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cons;
    }
}
