/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCSKH;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author AS
 */
public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrame() {
        // Cấu hình JFrame cơ bản
        setTitle("Đăng nhập hệ thống CSKH");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Căn giữa màn hình
        setLayout(new GridBagLayout()); // Dùng layout này để căn giữa form

        initUI();
    }

    private void initUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các phần tử
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label Tiêu đề
        JLabel lblTitle = new JLabel("LOGIN SYSTEM", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitle, gbc);

        // Username
        gbc.gridwidth = 1; gbc.gridy = 1;
        add(new JLabel("Tài khoản:"), gbc);
        
        txtUsername = new JTextField(20);
        gbc.gridx = 1;
        add(txtUsername, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Mật khẩu:"), gbc);
        
        txtPassword = new JPasswordField(20);
        gbc.gridx = 1;
        add(txtPassword, gbc);

        // Button Login
        btnLogin = new JButton("Đăng nhập");
        btnLogin.setBackground(new Color(41, 128, 185)); // Màu xanh đẹp
        btnLogin.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(btnLogin, gbc);

        // Xử lý sự kiện nút bấm
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String userText = txtUsername.getText();
        String passText = new String(txtPassword.getPassword());

        User loggedUser = null;

        // --- GIẢ LẬP CHECK DỮ LIỆU ---
        // Vì class User của bạn dùng getter/setter, ta khởi tạo kiểu này:
        
        if (userText.equals("admin") && passText.equals("123")) {
            loggedUser = new User();
            loggedUser.setId(1);
            loggedUser.setUsername("admin");
            loggedUser.setRole("ADMIN");
            
        } else if (userText.equals("staff") && passText.equals("123")) {
            loggedUser = new User();
            loggedUser.setId(2);
            loggedUser.setUsername("staff");
            loggedUser.setRole("STAFF");
            
        } else if (userText.equals("sup") && passText.equals("123")) {
            loggedUser = new User();
            loggedUser.setId(3);
            loggedUser.setUsername("supervisor");
            loggedUser.setRole("SUPERVISOR");
        }
        // -----------------------------

        if (loggedUser != null) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công! Role: " + loggedUser.getRole());
            
            // Chuyển sang MainFrame (Lưu ý: Bạn cần sửa MainFrame để nhận User kiểu mới này)
            new MainFrame(loggedUser).setVisible(true);
            this.dispose(); // Đóng form login
        } else {
            JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}