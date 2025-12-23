/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bt4;

import javax.swing.*;

public class LoginForm extends JFrame {

    public LoginForm() {

        setTitle("Đăng nhập");
        setSize(450, 300);
        setLocationRelativeTo(null);       // căn giữa màn hình
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);                    // dùng layout tự do giống hình

        // Label user
        JLabel lblUser = new JLabel("Nhập user");
        lblUser.setBounds(50, 50, 100, 30);
        add(lblUser);

        // TextField user
        JTextField txtUser = new JTextField();
        txtUser.setBounds(150, 50, 220, 30);
        add(txtUser);

        // Label pass
        JLabel lblPass = new JLabel("Nhập pass");
        lblPass.setBounds(50, 110, 100, 30);
        // PasswordField
        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(150, 110, 220, 30);
        add(txtPass);

        // Button
        JButton btnLogin = new JButton("Đăng nhập");
        btnLogin.setBounds(150, 170, 120, 35);
        add(btnLogin);

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}

