package QLNS.dao;

import QLNS.model.Thuong;
import QLNS.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThuongDAO {

    public List<Thuong> getAll() {
        List<Thuong> list = new ArrayList<>();
        String sql = "SELECT * FROM Thuong";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Thuong t = new Thuong();
                t.setMaThuong(rs.getString("MaThuong"));
                t.setSoTien(rs.getDouble("SoTien"));
                t.setLyDo(rs.getString("LyDo"));
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(Thuong t) {
        String sql = "INSERT INTO Thuong(MaThuong, SoTien, LyDo) VALUES (?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getMaThuong());
            ps.setDouble(2, t.getSoTien());
            ps.setString(3, t.getLyDo());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Thuong t, String maCu) {
        String sql = "UPDATE Thuong SET MaThuong=?, SoTien=?, LyDo=? WHERE MaThuong=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getMaThuong());
            ps.setDouble(2, t.getSoTien());
            ps.setString(3, t.getLyDo());
            ps.setString(4, maCu); // Điều kiện WHERE
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String maThuong) {
        String sql = "DELETE FROM Thuong WHERE MaThuong=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maThuong);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}