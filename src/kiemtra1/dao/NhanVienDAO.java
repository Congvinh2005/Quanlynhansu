/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kiemtra1.dao;

import java.sql.*;
import kiemtra1.model.NhanVien;

/**
 *
 * @author AS
 */
public class NhanVienDAO {

    public boolean exists(String maNV) throws Exception {
        String sql = "SELECT manv FROM nhanvien WHERE manv = ?";
        Connection c = DBConnection.getConn();
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, maNV);
        ResultSet rs = ps.executeQuery();
        boolean tonTai = rs.next();
        c.close();
        return tonTai;
    }

    public void insert(NhanVien nv) throws Exception {
        String sql = "INSERT INTO nhanvien VALUES (?,?,?,?,?,?,?,?)";
        Connection c = DBConnection.getConn();
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, nv.getMaNV());
        ps.setString(2, nv.getHoTen());
        ps.setString(3, nv.getNgaySinh());
        ps.setString(4, nv.getDiaChi());
        ps.setString(5, nv.getGioiTinh());
        ps.setString(6, nv.getCongViec());
        ps.setDouble(7, nv.getHsl());
        ps.setDouble(8, nv.getLuong());
        ps.executeUpdate();
        c.close();
    }
}
