/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kiemtra2.dao;

import java.sql.*;

/**
 *
 * @author AS
 */
public class DBConnection {

    public static Connection getConn() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/qlsv", "root", ""
        );
    }
}
