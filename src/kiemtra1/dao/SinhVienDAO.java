/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kiemtra1.dao;

import java.sql.*;
import kiemtra1.model.SinhVien;

/**
 *
 * @author AS
 */
public class SinhVienDAO {

    public boolean exists(String maSV) throws Exception {
        String sql = "SELECT masv FROM sinhvien WHERE masv = ?";
        Connection c = DBConnection.getConn();
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, maSV);
        ResultSet rs = ps.executeQuery();
        boolean tonTai = rs.next();
        c.close();
        return tonTai;
    }

    public void insert(SinhVien sv) throws Exception {
        String sql = "INSERT INTO sinhvien VALUES (?,?,?,?,?,?,?)";
        Connection c = DBConnection.getConn();
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, sv.getMaSV());
        ps.setString(2, sv.getHoTen());
        ps.setString(3, sv.getNgaySinh());
        ps.setString(4, sv.getDiaChi());
        ps.setString(5, sv.getGioiTinh());
        ps.setDouble(6, sv.getDiemTichLuy());
        ps.setDouble(7, sv.getDiemTB());
        ps.executeUpdate();
        c.close();
    }
}
