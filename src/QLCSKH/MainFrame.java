/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCSKH;

/**
 *
 * @author AS
 */
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private User currentUser;
    private JTabbedPane tabbedPane;

    public MainFrame(User user) {
        this.currentUser = user;
        setTitle("Hệ thống quản lý CSKH - Xin chào: " + user.getUsername());
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initUI();
    }

    private void initUI() {
        // 1. Header (Thanh trên cùng)
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.add(new JLabel("Role: " + currentUser.getRole()));
        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            this.dispose();
        });
        headerPanel.add(btnLogout);
        add(headerPanel, BorderLayout.NORTH);

        // 2. Nội dung chính (TabbedPane)
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        // --- LOGIC PHÂN QUYỀN (Thêm tab dựa theo Role) ---
        
        // Tab Ticket: Ai cũng có (Nhưng nội dung bên trong sẽ khác nhau nếu code sâu hơn)
        tabbedPane.addTab("Quản lý Yêu Cầu (Ticket)", new TicketPanel(currentUser));

        // Tab Thống kê: Chỉ Admin và Supervisor
        if (currentUser.getRole().equals("ADMIN") || currentUser.getRole().equals("SUPERVISOR")) {
            JPanel statsPanel = new JPanel();
            statsPanel.add(new JLabel("Đây là màn hình THỐNG KÊ (Biểu đồ, Report...)"));
            tabbedPane.addTab("Báo cáo & Thống kê", statsPanel);
        }

        // Tab Quản trị hệ thống: Chỉ Admin
        if (currentUser.getRole().equals("ADMIN")) {
            JPanel adminPanel = new JPanel();
            adminPanel.add(new JLabel("Đây là màn hình QUẢN LÝ USER & DANH MỤC"));
            tabbedPane.addTab("Quản trị hệ thống", adminPanel);
        }
    }
}
