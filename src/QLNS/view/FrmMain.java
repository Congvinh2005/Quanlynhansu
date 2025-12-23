package QLNS.view;

import QLNS.controller.MainController;
import javax.swing.*;
import java.awt.*;

public class FrmMain extends JFrame {

    // ===== MENU BAR =====
    JMenuBar menuBar = new JMenuBar();

    JMenu mnuHeThong = new JMenu("Hệ thống");
    JMenuItem mniQuanLyTaiKhoan = new JMenuItem("Quản lý tài khoản");
    JMenuItem mniDangXuat = new JMenuItem("Đăng xuất");

    JMenu mnuDanhMuc = new JMenu("Danh mục");
    JMenuItem mniThongTinCaNhan = new JMenuItem("Thông tin cá nhân");
    JMenuItem mniPhongBan = new JMenuItem("Phòng ban");
    JMenuItem mniChucVu = new JMenuItem("Chức vụ");
    JMenuItem mniLuong = new JMenuItem("Lương");
    JMenuItem mniThuong = new JMenuItem("Thưởng");
    JMenuItem mniPhuCap = new JMenuItem("Phụ cấp");

    JMenu mnuQuanLy = new JMenu("Quản lý");
    JMenuItem mniQLNS = new JMenuItem("Quản lý nhân sự");
    JMenuItem mniBangLuong = new JMenuItem("Bảng lương nhân viên");
    

    JMenu mnuChucNang = new JMenu("Chức năng");
    JMenuItem mniTraCuu = new JMenuItem("Tra cứu");
    JMenuItem mniTroGiup = new JMenuItem("Trợ giúp");

    JMenu mnuBaoCao = new JMenu("Báo cáo");

    // ===== CONTENT =====
    private JPanel pnlContent;
    private JLabel lblStatus;

    private String maNhanVien;
    private String loaiTK;

    public FrmMain(String maNhanVien, String loaiTK) {
        this.maNhanVien = maNhanVien;
        this.loaiTK = loaiTK;

        initUI();
        new MainController(this);
    }

    private void initUI() {
        setTitle("Trang chủ - QLNS");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== MENU =====
        mnuHeThong.add(mniQuanLyTaiKhoan);
        mnuHeThong.add(mniDangXuat);
        mnuHeThong.addSeparator();   

        mnuDanhMuc.add(mniThongTinCaNhan);
        mnuDanhMuc.add(mniPhongBan);
        mnuDanhMuc.add(mniChucVu);
        mnuDanhMuc.add(mniLuong);
        mnuDanhMuc.add(mniThuong);
        mnuDanhMuc.add(mniPhuCap);

        mnuQuanLy.add(mniQLNS);
        mnuQuanLy.add(mniBangLuong);

        mnuChucNang.add(mniTraCuu);
        mnuChucNang.add(mniTroGiup);

        menuBar.add(mnuHeThong);
        menuBar.add(mnuDanhMuc);
        menuBar.add(mnuQuanLy);
        menuBar.add(mnuChucNang);
        menuBar.add(mnuBaoCao);

        setJMenuBar(menuBar);

        // ===== PANEL CONTENT (GIỐNG MAINFRAME) =====
        pnlContent = new JPanel(new BorderLayout());
        pnlContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblWelcome = new JLabel(
                "CHÀO MỪNG ĐẾN VỚI HỆ THỐNG QUẢN LÝ NHÂN SỰ",
                SwingConstants.CENTER
        );
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 28));
        lblWelcome.setForeground(new Color(80, 80, 80));

        JLabel lblFooter = new JLabel(
                "By Vinh - Thanh - Quang Anh",
                SwingConstants.CENTER
        );
        lblFooter.setFont(new Font("Arial", Font.ITALIC, 16));

        pnlContent.add(lblWelcome, BorderLayout.CENTER);
        pnlContent.add(lblFooter, BorderLayout.SOUTH);

        add(pnlContent, BorderLayout.CENTER);

        // ===== STATUS BAR =====
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBorder(BorderFactory.createEtchedBorder());
        statusPanel.setBackground(new Color(240, 240, 240));

        lblStatus = new JLabel(" Xin chào: " + maNhanVien + " | Loại tài khoản: " + loaiTK);
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusPanel.add(lblStatus);

        add(statusPanel, BorderLayout.SOUTH);
    }

    // ===== HÀM ĐỔI NỘI DUNG (SAU NÀY DÙNG PANEL) =====
    public void setContentPanel(JPanel panel) {
        pnlContent.removeAll();
        pnlContent.add(panel, BorderLayout.CENTER);
        pnlContent.revalidate();
        pnlContent.repaint();
    }

    // ===== GETTER =====
    public JMenuItem getMniDangXuat() { return mniDangXuat; }
    public JMenuItem getmniQuanLyTaiKhoan() { return mniQuanLyTaiKhoan; }
    public JMenuItem getMniQLNS() { return mniQLNS; }
    public JMenuItem getMniPhongBan() { return mniPhongBan; }
    public JMenuItem getMniChucVu() { return mniChucVu; }
    public JMenuItem getMniPhuCap() { return mniPhuCap; }
    public JMenuItem getMniLuong() { return mniLuong; }
    public JMenuItem getMniThuong() { return mniThuong; }
    public JMenuItem getMniThongTinCaNhan() { return mniThongTinCaNhan; }
    public JMenuItem getMniBangLuong() { return mniBangLuong; }
    public JMenuItem getMniTraCuu() { return mniTraCuu; }
    public JMenuItem getMniTroGiup() { return mniTroGiup; }

    public JMenu getMnuQuanLy() { return mnuQuanLy; }
    public JMenu getMnuBaoCao() { return mnuBaoCao; }

    public String getLoaiTK() { return loaiTK; }
    public String getMaNhanVien() { return maNhanVien; }
}
