package QLNS.controller;

import QLNS.view.*;
// Import các Controller cần thiết
import QLNS.controller.TaiKhoanController;
import QLNS.controller.NhanVienController;

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
            // ... các phân quyền khác giữ nguyên
            view.getMniPhongBan().setEnabled(false);
            view.getMniChucVu().setEnabled(false);
            view.getMniPhuCap().setEnabled(false);
            view.getMniLuong().setEnabled(false);
            view.getMniThuong().setEnabled(false);
        }
    }

    private void initEvents() {

        // ===== 1. ĐĂNG XUẤT =====
        view.getMniDangXuat().addActionListener(e -> {
            view.dispose();
            new FrmLogin().setVisible(true);
        });

        // ===== 2. QUẢN LÝ TÀI KHOẢN (ĐÃ SỬA) =====
        view.getmniQuanLyTaiKhoan().addActionListener(e -> {
            // Bước 1: Tạo View (Giao diện rỗng)
            FrmQLTK viewTK = new FrmQLTK();

            new TaiKhoanController(viewTK);

            // Bước 3: Gọi Controller phụ (Load danh sách Mã NV vào ComboBox)
            new NhanVienController(viewTK);

            // Bước 4: Hiển thị lên Main
            openPanel(viewTK);
        });

        // ===== 3. THÔNG TIN CÁ NHÂN / NHÂN VIÊN (ĐÃ SỬA) =====
        view.getMniThongTinCaNhan().addActionListener(e -> {
            // Bước 1: Tạo View
            FrmThongTinCaNhan viewNV = new FrmThongTinCaNhan();

            // Bước 2: Gọi Controller (Xử lý Thêm/Sửa/Xóa NV)
            new NhanVienController(viewNV);

            // Bước 3: Hiển thị
            openPanel(viewNV);
        });

        view.getMniPhongBan().addActionListener(e -> {
            FrmPhongBan viewPB = new FrmPhongBan();
            new PhongBanController(viewPB); // <--- QUAN TRỌNG: GỌI CONTROLLER
            openPanel(viewPB);
        });

        view.getMniChucVu().addActionListener(e -> {
            FrmChucVu viewCV = new FrmChucVu();
            new ChucVuController(viewCV); // <--- QUAN TRỌNG: Gọi Controller
            openPanel(viewCV);
        });

        view.getMniLuong().addActionListener(e -> {
            FrmLuong viewLuong = new FrmLuong();
            new LuongController(viewLuong); // <--- KẾT NỐI CONTROLLER
            openPanel(viewLuong);
        });

        view.getMniThuong().addActionListener(e -> {
            FrmThuong viewThuong = new FrmThuong();
            new ThuongController(viewThuong); // <--- KẾT NỐI CONTROLLER
            openPanel(viewThuong);
        });

        view.getMniPhuCap().addActionListener(e -> {
            FrmPhuCap viewPC = new FrmPhuCap();
            new PhuCapController(viewPC); // <--- KẾT NỐI CONTROLLER
            openPanel(viewPC);
        });

        view.getMniQLNS().addActionListener(e -> {
            FrmQLNS viewQLNS = new FrmQLNS();
            new QLNSController(viewQLNS); // Kết nối
            openPanel(viewQLNS);
        });

        view.getMniBangLuong().addActionListener(e -> {
            FrmBangLuong viewBL = new FrmBangLuong();
            new BangLuongController(viewBL);
            openPanel(viewBL);
        });
    }

    // ===== HIỂN THỊ PANEL =====
    private void openPanel(JPanel panel) {
        view.setContentPanel(panel);
    }
}
