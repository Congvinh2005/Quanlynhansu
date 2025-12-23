package baitapjava;

import java.sql.*;
import javax.swing.JOptionPane;

public class controller_login {
    
    private String url = "jdbc:mysql://localhost:3307/qlnv";
    private String user = "root";
    private String password = "";

    public controller_login() {
    }

    // Hàm trả về boolean: True nếu đăng nhập đúng, False nếu sai
    public boolean check_login(model_login tai_khoan) {
        boolean ketQua = false; // 1. Mặc định kết quả là SAI (False)

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tai_khoan.user);
            stmt.setString(2, tai_khoan.pass);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // 2. Nếu tìm thấy user trong DB -> Đổi kết quả thành ĐÚNG (True)
                JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
                ketQua = true; 
            } else {
                JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                ketQua = false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối CSDL!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            ketQua = false;
        }
        
        // 3. Quan trọng: Luôn trả về biến kết quả ở dòng cuối cùng
        return ketQua; 
    }
}