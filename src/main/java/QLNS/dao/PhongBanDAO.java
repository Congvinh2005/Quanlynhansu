package QLNS.dao;

import QLNS.model.PhongBan;
import QLNS.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhongBanDAO {

    public List<PhongBan> getAll() {
        List<PhongBan> list = new ArrayList<>();

        String sql = "SELECT * FROM PhongBan"; 
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                PhongBan pb = new PhongBan();
                pb.setMaPB(rs.getString("MaPhongBan")); 
                pb.setTenPB(rs.getString("TenPhongBan"));
                pb.setNgayThanhLap(rs.getString("NgayThanhLap"));
                pb.setGhiChu(rs.getString("GhiChu"));
                list.add(pb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(PhongBan pb) {
        String sql = "INSERT INTO PhongBan(MaPhongBan, TenPhongBan, NgayThanhLap, GhiChu) VALUES (?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, pb.getMaPB());
            ps.setString(2, pb.getTenPB());
            ps.setString(3, pb.getNgayThanhLap());
            ps.setString(4, pb.getGhiChu());
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(PhongBan pb, String maPhongBanCu) {
        String sql = "UPDATE PhongBan SET MaPhongBan=?, TenPhongBan=?, NgayThanhLap=?, GhiChu=? WHERE MaPhongBan=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, pb.getMaPB());
            ps.setString(2, pb.getTenPB());
            ps.setString(3, pb.getNgayThanhLap());
            ps.setString(4, pb.getGhiChu());
            ps.setString(5, maPhongBanCu); // Điều kiện WHERE
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String maPhongBan) {
        String sql = "DELETE FROM PhongBan WHERE MaPhongBan=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, maPhongBan);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}