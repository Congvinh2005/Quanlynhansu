package QLNS.dao;

import QLNS.model.NhanVien;
import QLNS.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {

    /* ================= LẤY TẤT CẢ NHÂN VIÊN ================= */
    public List<NhanVien> getAll() {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNhanVien")); 
                nv.setHoTen(rs.getString("HoTen"));
                nv.setNgaySinh(rs.getString("NgaySinh"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setSdt(rs.getString("SDT"));
                list.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    /* ================= LẤY LIST MÃ NHÂN VIÊN (Cho ComboBox) ================= */
    public List<String> getAllMaNV() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT MaNhanVien FROM NhanVien"; // SỬA: MaNV -> MaNhanVien

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString("MaNhanVien"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= THÊM NHÂN VIÊN ================= */
    public boolean insert(NhanVien nv) {
        String sql = "INSERT INTO NhanVien(MaNhanVien, HoTen, NgaySinh, DiaChi, GioiTinh, SDT) VALUES (?,?,?,?,?,?)";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getHoTen());
            ps.setString(3, nv.getNgaySinh());
            ps.setString(4, nv.getDiaChi());
            ps.setString(5, nv.getGioiTinh());
            ps.setString(6, nv.getSdt());
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= CẬP NHẬT NHÂN VIÊN ================= */
    public boolean update(NhanVien nv, String maNV_Cu) {
        String sql = "UPDATE NhanVien SET MaNhanVien=?, HoTen=?, NgaySinh=?, DiaChi=?, GioiTinh=?, SDT=? WHERE MaNhanVien=?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getHoTen());
            ps.setString(3, nv.getNgaySinh());
            ps.setString(4, nv.getDiaChi());
            ps.setString(5, nv.getGioiTinh());
            ps.setString(6, nv.getSdt());
            ps.setString(7, maNV_Cu); // Điều kiện WHERE

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= XÓA NHÂN VIÊN ================= */
    public boolean delete(String maNV) {
        String sql = "DELETE FROM NhanVien WHERE MaNhanVien=?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, maNV);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /* Thêm vào QLNS.dao.NhanVienDAO */

    // Hàm lấy dữ liệu hiển thị (JOIN bảng)
    public List<Object[]> getAllHienThi() {
        List<Object[]> list = new ArrayList<>();
        // LEFT JOIN để vẫn hiện nhân viên dù chưa có phòng ban/chức vụ
        String sql = "SELECT nv.MaNhanVien, nv.HoTen, pb.TenPhongBan, cv.TenChucVu " +
                "FROM NhanVien nv " +
                "LEFT JOIN PhongBan pb ON nv.MaPhongBan = pb.MaPhongBan " +
                "LEFT JOIN ChucVu cv ON nv.MaChucVu = cv.MaChucVu";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                // Xử lý null -> "Chưa có"
                String pb = rs.getString(3);
                if (pb == null) pb = "Chưa có";

                String cv = rs.getString(4);
                if (cv == null) cv = "Chưa có";

                list.add(new Object[]{ma, ten, pb, cv});
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // Hàm phân công (Cập nhật FK)
    public boolean phanCong(String maNV, String maPB, String maCV) {
        String sql = "UPDATE NhanVien SET MaPhongBan=?, MaChucVu=? WHERE MaNhanVien=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Nếu chuỗi rỗng hoặc null thì set NULL trong database
            if (maPB == null || maPB.isEmpty()) ps.setNull(1, java.sql.Types.VARCHAR);
            else ps.setString(1, maPB);

            if (maCV == null || maCV.isEmpty()) ps.setNull(2, java.sql.Types.VARCHAR);
            else ps.setString(2, maCV);

            ps.setString(3, maNV);

            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}