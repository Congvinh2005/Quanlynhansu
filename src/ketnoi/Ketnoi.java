package ketnoi;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ketnoi {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307";
        var user = "root";
        var password = "";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Ket noi thanh cong");
            System.out.println(conn.getCatalog());
        } catch (SQLException ex) {
            Logger.getLogger(Ketnoi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}