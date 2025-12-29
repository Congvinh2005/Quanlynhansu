package QLNS.dao;

import QLNS.model.Luong;
import QLNS.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LuongDAO {

    public List<Luong> getAll() {
        List<Luong> list = new ArrayList<>();
        String sql = "SELECT * FROM Luong";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                Luong l = new Luong();
                l.setMaLuong(rs.getString("MaLuong"));
                l.setLuongCoBan(rs.getDouble("LuongCoBan"));
                l.setGhiChu(rs.getString("GhiChu"));
                list.add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(Luong l) {
        String sql = "INSERT INTO Luong(MaLuong, LuongCoBan, GhiChu) VALUES (?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, l.getMaLuong());
            ps.setDouble(2, l.getLuongCoBan());
            ps.setString(3, l.getGhiChu());
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Luong l, String maLuongCu) {
        String sql = "UPDATE Luong SET MaLuong=?, LuongCoBan=?, GhiChu=? WHERE MaLuong=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, l.getMaLuong());
            ps.setDouble(2, l.getLuongCoBan());
            ps.setString(3, l.getGhiChu());
            ps.setString(4, maLuongCu); // Điều kiện WHERE
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String maLuong) {
        String sql = "DELETE FROM Luong WHERE MaLuong=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, maLuong);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}