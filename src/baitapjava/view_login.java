/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baitapjava;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;

/**
 *
 * @author phant
 */
public class view_login {
     JFrame frame;
    JTextField tf_username;
    JPasswordField tf_password;
    JLabel lbl_username, lbl_password;
    JButton btn_login;
    controller_login LoginController;
    public view_login() {
        frame = new JFrame();
        frame.setLayout(new BorderLayout(5, 5));
        frame.setSize(500, 500);
        frame.setTitle("Dang nhap");

        tf_username = new JTextField();
        tf_password = new JPasswordField();
        lbl_username = new JLabel("Username");
        lbl_password = new JLabel("Password");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel, BorderLayout.NORTH);
        panel.add(lbl_username);
        panel.add(tf_username);

        panel.add(lbl_password);
        panel.add(tf_password);
        btn_login = new JButton("Login");
        // Trong file view_login.java

btn_login.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Lấy dữ liệu
        model_login model = new model_login();
        model.user = tf_username.getText();
        model.pass = new String(tf_password.getPassword());
        
        controller_login control = new controller_login();
        
        // Kiểm tra đăng nhập
        if (control.check_login(model)) {
            // Đóng form đăng nhập
            frame.dispose(); 
            
            // MỞ FORM QUẢN LÝ NHÂN VIÊN
            new QuanLiNhanVien(); 
        }
    }
});
        frame.add(btn_login, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);    
    }
public static void main(String[] args){
        view_login v= new view_login();
    }
}