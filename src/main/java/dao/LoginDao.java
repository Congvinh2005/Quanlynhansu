package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DBConnection;

public class LoginDao {

    private Connection con;

    public LoginDao() throws Exception {
        con = DBConnection.getConnection();

        if (con == null) {
            throw new Exception("Lỗi kết nối database");
        }
    }

    public boolean checkLogin(String user, String pass) {
        try {

            String sql = "SELECT * FROM Taikhoan WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
