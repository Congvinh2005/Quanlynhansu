package QLNS.controller;

import QLNS.view.FrmChucVu;
import QLNS.view.FrmLogin;
import QLNS.view.FrmLuong;
import QLNS.view.FrmMain;
import QLNS.view.FrmPhongBan;
import QLNS.view.FrmPhuCap;
import QLNS.view.FrmQLTK;
import QLNS.view.FrmThuong;

import javax.swing.*;

public class MainController {

    private FrmMain view;

    public MainController(FrmMain view) {
        this.view = view;
        updateMenuByRole();
        initEvents();
    }

    // ===== PHÂN QUYỀN (GIỮ NGUYÊN) =====
    private void updateMenuByRole() {
        if (view.getLoaiTK().equalsIgnoreCase("Nhân viên")) {
            view.getMnuQuanLy().setEnabled(false);
            view.getmniQuanLyTaiKhoan().setEnabled(false);
            view.getMnuBaoCao().setEnabled(false);
            view.getMniPhongBan().setEnabled(false);
            view.getMniChucVu().setEnabled(false);
            view.getMniPhuCap().setEnabled(false);
            view.getMniLuong().setEnabled(false);
            view.getMniThuong().setEnabled(false);
        }
    }

    private void initEvents() {

        // ===== ĐĂNG XUẤT =====
        view.getMniDangXuat().addActionListener(e -> {
            view.dispose();
            new FrmLogin().setVisible(true);
        });

        // ===== QUẢN LÝ TÀI KHOẢN =====
        view.getmniQuanLyTaiKhoan().addActionListener(e ->
                openPanel(new FrmQLTK())
        );
        view.getMniPhongBan().addActionListener(e ->
                openPanel(new FrmPhongBan())
        );
        view.getMniChucVu().addActionListener(e ->
                openPanel(new FrmChucVu())
        );
        view.getMniLuong().addActionListener(e ->
                openPanel(new FrmLuong())
        );
        view.getMniThuong().addActionListener(e ->
                openPanel(new FrmThuong())
        );
        view.getMniPhuCap().addActionListener(e ->
                openPanel(new FrmPhuCap())
        );
    }

    // ===== HIỂN THỊ PANEL =====
    private void openPanel(JPanel panel) {
        view.setContentPanel(panel);
    }
}
