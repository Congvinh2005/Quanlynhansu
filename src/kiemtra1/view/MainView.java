/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kiemtra1.view;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author AS
 */


public class MainView extends JFrame {

    // ====== MENU ======
    JMenuItem mnuSV, mnuNV;

    // ====== CARD ======
    CardLayout cardLayout;
    JPanel mainPanel;

    // ====== SINH VIÊN ======
    public JTextField sv_ma, sv_ten, sv_ns, sv_dc, sv_dtl;
    public JComboBox<String> sv_gt;
    public JButton btnSVThem, btnSVLuu;

    // ====== NHÂN VIÊN ======
    public JTextField nv_ma, nv_ten, nv_ns, nv_dc, nv_cv, nv_hsl;
    public JComboBox<String> nv_gt;
    public JButton btnNVThem, btnNVLuu;

    public MainView() {
        setTitle("Quản lý Sinh viên & Nhân viên");
        setSize(650, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        taoMenu();
        taoCard();

        setVisible(true);
    }

    // ================= MENU =================
    private void taoMenu() {
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Quản lý");

        mnuSV = new JMenuItem("Quản lý Sinh viên");
        mnuNV = new JMenuItem("Quản lý Nhân viên");

        menu.add(mnuSV);
        menu.add(mnuNV);
        bar.add(menu);

        setJMenuBar(bar);
    }

    // ================= CARD =================
    private void taoCard() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(panelSinhVien(), "SV");
        mainPanel.add(panelNhanVien(), "NV");

        add(mainPanel);

        // xử lý click menu
        mnuSV.addActionListener(e -> cardLayout.show(mainPanel, "SV"));
        mnuNV.addActionListener(e -> cardLayout.show(mainPanel, "NV"));
    }

    // ================= PANEL SINH VIÊN =================
    private JPanel panelSinhVien() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.fill = GridBagConstraints.HORIZONTAL;

        sv_ma = new JTextField();
        sv_ten = new JTextField();
        sv_ns = new JTextField();
        sv_dc = new JTextField();
        sv_dtl = new JTextField();
        sv_gt = new JComboBox<>(new String[]{"Nam", "Nữ"});

        btnSVThem = new JButton("Thêm");
        btnSVLuu = new JButton("Lưu");

        int y = 0;
        addRow(p, g, y++, "Mã SV", sv_ma);
        addRow(p, g, y++, "Họ tên", sv_ten);
        addRow(p, g, y++, "Ngày sinh", sv_ns);
        addRow(p, g, y++, "Địa chỉ", sv_dc);
        addRow(p, g, y++, "Giới tính", sv_gt);
        addRow(p, g, y++, "Điểm tích lũy", sv_dtl);

        g.gridx = 1; g.gridy = y;
        p.add(btnSVThem, g);
        g.gridx = 2;
        p.add(btnSVLuu, g);

        return p;
    }

    // ================= PANEL NHÂN VIÊN =================
    private JPanel panelNhanVien() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.fill = GridBagConstraints.HORIZONTAL;

        nv_ma = new JTextField();
        nv_ten = new JTextField();
        nv_ns = new JTextField();
        nv_dc = new JTextField();
        nv_cv = new JTextField();
        nv_hsl = new JTextField();
        nv_gt = new JComboBox<>(new String[]{"Nam", "Nữ"});

        btnNVThem = new JButton("Thêm");
        btnNVLuu = new JButton("Lưu");

        int y = 0;
        addRow(p, g, y++, "Mã NV", nv_ma);
        addRow(p, g, y++, "Họ tên", nv_ten);
        addRow(p, g, y++, "Ngày sinh", nv_ns);
        addRow(p, g, y++, "Địa chỉ", nv_dc);
        addRow(p, g, y++, "Giới tính", nv_gt);
        addRow(p, g, y++, "Công việc", nv_cv);
        addRow(p, g, y++, "Hệ số lương", nv_hsl);

        g.gridx = 1; g.gridy = y;
        p.add(btnNVThem, g);
        g.gridx = 2;
        p.add(btnNVLuu, g);

        return p;
    }

    // ================= HÀM DÙNG CHUNG =================
    private void addRow(JPanel p, GridBagConstraints g, int y,
                        String label, Component c) {
        g.gridx = 0; g.gridy = y;
        p.add(new JLabel(label), g);

        g.gridx = 1; g.gridy = y;
        g.gridwidth = 2;
        p.add(c, g);
        g.gridwidth = 1;
    }
}
