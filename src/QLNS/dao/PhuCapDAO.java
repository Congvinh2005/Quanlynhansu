package QLNS.dao;

import QLNS.model.PhuCap;
import QLNS.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhuCapDAO {

    public List<PhuCap> getAll() {
        List<PhuCap> list = new ArrayList<>();
        String sql = "SELECT * FROM PhuCap";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PhuCap pc = new PhuCap();
                pc.setMaPC(rs.getString("MaPhuCap"));
                pc.setTenPC(rs.getString("TenPhuCap"));
                pc.setTienPC(rs.getDouble("TienPhuCap"));
                pc.setNgayHieuLuc(rs.getString("NgayHieuLuc"));
                list.add(pc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(PhuCap pc) {
        String sql = "INSERT INTO PhuCap(MaPhuCap, TenPhuCap, TienPhuCap, NgayHieuLuc) VALUES (?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pc.getMaPC());
            ps.setString(2, pc.getTenPC());
            ps.setDouble(3, pc.getTienPC());
            ps.setString(4, pc.getNgayHieuLuc());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(PhuCap pc, String maCu) {
        String sql = "UPDATE PhuCap SET MaPhuCap=?, TenPhuCap=?, TienPhuCap=?, NgayHieuLuc=? WHERE MaPhuCap=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pc.getMaPC());
            ps.setString(2, pc.getTenPC());
            ps.setDouble(3, pc.getTienPC());
            ps.setString(4, pc.getNgayHieuLuc());
            ps.setString(5, maCu);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String maPC) {
        String sql = "DELETE FROM PhuCap WHERE MaPhuCap=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maPC);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}