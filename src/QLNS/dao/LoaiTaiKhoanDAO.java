package QLNS.dao;

import QLNS.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoaiTaiKhoanDAO {

    public List<String> getAllLoaiTK() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT LoaiTaiKhoan FROM loaitaikhoan";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(rs.getString("LoaiTaiKhoan"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
