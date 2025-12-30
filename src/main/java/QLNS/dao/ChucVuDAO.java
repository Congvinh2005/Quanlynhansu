package QLNS.dao;

import QLNS.model.ChucVu;
import QLNS.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChucVuDAO {

    public List<ChucVu> getAll() {
        List<ChucVu> list = new ArrayList<>();
        String sql = "SELECT * FROM ChucVu"; 
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                ChucVu cv = new ChucVu();
                cv.setMaCV(rs.getString("MaChucVu")); 
                cv.setTenCV(rs.getString("TenChucVu"));
                cv.setMoTa(rs.getString("MoTa"));
                list.add(cv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(ChucVu cv) {
        String sql = "INSERT INTO ChucVu(MaChucVu, TenChucVu, MoTa) VALUES (?,?,?)";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, cv.getMaCV());
            ps.setString(2, cv.getTenCV());
            ps.setString(3, cv.getMoTa());
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(ChucVu cv, String maCV_Cu) {
        String sql = "UPDATE ChucVu SET MaChucVu=?, TenChucVu=?, MoTa=? WHERE MaChucVu=?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, cv.getMaCV());
            ps.setString(2, cv.getTenCV());
            ps.setString(3, cv.getMoTa());
            ps.setString(4, maCV_Cu); // Điều kiện WHERE
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String maCV) {
        String sql = "DELETE FROM ChucVu WHERE MaChucVu=?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, maCV);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}