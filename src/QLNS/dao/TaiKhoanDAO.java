package QLNS.dao;

import QLNS.model.TaiKhoan;
import QLNS.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {

    /* ================= LOGIN (GIỮ NGUYÊN) ================= */
    public TaiKhoan login(String tenTK, String matKhau) {
        String sql = "SELECT MaNhanVien, LoaiTaiKhoan "
                + "FROM TaiKhoan WHERE TenTaiKhoan=? AND MatKhau=?";

        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenTK);
            ps.setString(2, matKhau);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new TaiKhoan(
                        rs.getString("MaNhanVien"),
                        rs.getString("LoaiTaiKhoan")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ================= LẤY TẤT CẢ TÀI KHOẢN ================= */
    public List<TaiKhoan> getAll() {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoan";

        try (
                Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setId(rs.getInt("ID"));
                tk.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
                tk.setMatKhau(rs.getString("MatKhau"));
                tk.setLoaiTaiKhoan(rs.getString("LoaiTaiKhoan"));
                tk.setMaNhanVien(rs.getString("MaNhanVien"));
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= THÊM TÀI KHOẢN ================= */
    public boolean insert(TaiKhoan tk) {
        String sql = "INSERT INTO TaiKhoan(TenTaiKhoan, MatKhau, LoaiTaiKhoan, MaNhanVien) "
                + "VALUES (?,?,?,?)";
        try (
                Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tk.getTenTaiKhoan());
            ps.setString(2, tk.getMatKhau());
            ps.setString(3, tk.getLoaiTaiKhoan());
            ps.setString(4, tk.getMaNhanVien());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= CẬP NHẬT TÀI KHOẢN ================= */
    public boolean update(TaiKhoan tk, String tenTaiKhoanCu) {
        // Câu SQL: Set tên MỚI cho dòng có tên CŨ
        String sql = "UPDATE TaiKhoan SET TenTaiKhoan=?, MatKhau=?, LoaiTaiKhoan=?, MaNhanVien=? WHERE TenTaiKhoan=?";

        try (
                Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // 1. Tên tài khoản MỚI (lấy từ object tk do người dùng nhập trên form)
            ps.setString(1, tk.getTenTaiKhoan());

            // 2. Mật khẩu
            ps.setString(2, tk.getMatKhau());

            // 3. Loại tài khoản
            ps.setString(3, tk.getLoaiTaiKhoan());

            // 4. Mã nhân viên
            ps.setString(4, tk.getMaNhanVien());

            // 5. Tên tài khoản CŨ (để tìm ra dòng cần sửa trong DB)
            ps.setString(5, tenTaiKhoanCu);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= XÓA TÀI KHOẢN ================= */
    public boolean delete(String tenTK) {
        String sql = "DELETE FROM TaiKhoan WHERE TenTaiKhoan=?";
        try (
                Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenTK);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
