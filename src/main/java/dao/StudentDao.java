package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Student;
import util.DBConnection;

public class StudentDao {

    private final Connection con;

    public StudentDao() throws Exception {
        con = DBConnection.getConnection();

        if (con == null) {

            throw new Exception("Không thể kết nối đến cơ sở dữ liệu!");
        }
    }

    public ArrayList<Student> getAll() {
        ArrayList<Student> list = new ArrayList<>();

        try (
                Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM Student");) {

            while (rs.next()) {
                Student s = new Student(
                        rs.getString("hoten"),
                        rs.getString("ngaysinh"),
                        rs.getString("diachi"),
                        rs.getString("gioitinh"),
                        rs.getString("masv"),
                        rs.getString("email"),
                        rs.getDouble("dtk")
                );
                list.add(s);
            }

        } catch (SQLException e) {
            // In lỗi SQL ra console để debug khi thất bại
            System.err.println("Lỗi SQL khi lấy danh sách sinh viên:");
            e.printStackTrace();
            // KHÔNG NÊN BỎ TRỐNG catch block
        }
        return list;
    }

    public boolean insert(Student s) {
        String sql = "INSERT INTO Student (hoten, ngaysinh, diachi, gioitinh, masv, email, dtk) VALUES (?,?,?,?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getHoTen());
            ps.setString(2, s.getNgaySinh());
            ps.setString(3, s.getDiaChi());
            ps.setString(4, s.getGioiTinh());
            ps.setString(5, s.getMasv());
            ps.setString(6, s.getEmail());
            ps.setDouble(7, s.getDTK());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi SQL khi thêm sinh viên:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Student s) {
        String sql = "UPDATE Student SET hoten=?, ngaysinh=?, diachi=?, gioitinh=?, email=?, dtk=? WHERE masv=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getHoTen());
            ps.setString(2, s.getNgaySinh());
            ps.setString(3, s.getDiaChi());
            ps.setString(4, s.getGioiTinh());
            ps.setString(5, s.getEmail());
            ps.setDouble(6, s.getDTK());
            ps.setString(7, s.getMasv());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi SQL khi cập nhật sinh viên:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String masv) {
        String sql = "DELETE FROM Student WHERE masv=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, masv);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi SQL khi xóa sinh viên:");
            e.printStackTrace();
            return false;
        }
    }
}
