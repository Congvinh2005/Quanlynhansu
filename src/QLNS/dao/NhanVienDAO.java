package QLNS.dao;

import QLNS.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {

    public List<String> getAllMaNV() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT MaNhanVien FROM nhanvien";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(rs.getString("MaNhanVien"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
