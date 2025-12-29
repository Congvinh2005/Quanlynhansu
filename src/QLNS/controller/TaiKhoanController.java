package QLNS.controller;

import QLNS.dao.TaiKhoanDAO;
import QLNS.model.TaiKhoan;
import QLNS.view.FrmQLTK;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors; // Cần thêm thư viện này để lọc tìm kiếm
import javax.swing.JOptionPane;

public class TaiKhoanController {

    private FrmQLTK view;
    private TaiKhoanDAO dao;

    public TaiKhoanController(FrmQLTK view) {
        this.view = view;

        try {
            dao = new TaiKhoanDAO();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Không kết nối được Database: " + e.getMessage());
        }

        new LoaiTaiKhoanController(view);
        new NhanVienController(view);

        loadTable();
        initEvents();
    }

    private void loadTable() {
        List<TaiKhoan> list = dao.getAll();
        view.showData(list);
    }

    private void initEvents() {
        // ================== 1. SỰ KIỆN THÊM (CÓ CHECK RỖNG + CHECK TRÙNG) ==================
        view.btnThem.addActionListener(e -> {
            try {
                TaiKhoan tk = view.getFormData();

                // --- BƯỚC 1: CHECK RỖNG (MỚI THÊM) ---
                if (tk.getTenTaiKhoan().trim().isEmpty()
                        || tk.getMatKhau().trim().isEmpty()
                        || tk.getLoaiTaiKhoan().trim().isEmpty()
                        || tk.getMaNhanVien().trim().isEmpty()) {

                    JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
                    return; // Dừng lại ngay, không làm gì tiếp theo
                }
                // ------------------------------------

                // --- BƯỚC 2: CHECK TRÙNG ---
                List<TaiKhoan> currentList = dao.getAll();
                boolean checkTrung = false;
                for (TaiKhoan existingTK : currentList) {
                    if (existingTK.getTenTaiKhoan().equalsIgnoreCase(tk.getTenTaiKhoan())) {
                        checkTrung = true;
                        break;
                    }
                }

                if (checkTrung) {
                    JOptionPane.showMessageDialog(view, "Tên tài khoản '" + tk.getTenTaiKhoan() + "' đã tồn tại! Vui lòng chọn tên khác.");
                    return;
                }
                // ---------------------------

                // --- BƯỚC 3: THÊM VÀO DATABASE ---
                if (dao.insert(tk)) {
                    JOptionPane.showMessageDialog(view, "Thêm thành công");
                    loadTable();
                    view.clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Thêm thất bại");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Lỗi thêm: " + ex.getMessage());
            }
        });

        // ================== 2. SỰ KIỆN SỬA (CÓ CHECK RỖNG) ==================
        // ... Trong initEvents() ...
        view.btnSua.addActionListener(e -> {
            try {
                // 1. Lấy tên tài khoản CŨ (đang chọn trên bảng) TRƯỚC khi lấy dữ liệu form
                String tenTK_Cu = view.getSelectedTenTK();

                if (tenTK_Cu == null) {
                    JOptionPane.showMessageDialog(view, "Vui lòng chọn tài khoản để sửa!");
                    return;
                }

                // 2. Lấy dữ liệu MỚI từ form (người dùng đã sửa tên trên textfield)
                TaiKhoan tk_Moi = view.getFormData();

                // --- Check rỗng nếu cần thiết ---
                if (tk_Moi.getTenTaiKhoan().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Tên tài khoản không được để trống!");
                    return;
                }

                // 3. Check trùng tên MỚI (nếu người dùng đổi tên, phải xem tên mới đã có chưa)
                // Chỉ check nếu tên MỚI khác tên CŨ
                if (!tk_Moi.getTenTaiKhoan().equalsIgnoreCase(tenTK_Cu)) {
                    List<TaiKhoan> list = dao.getAll();
                    for (TaiKhoan t : list) {
                        if (t.getTenTaiKhoan().equalsIgnoreCase(tk_Moi.getTenTaiKhoan())) {
                            JOptionPane.showMessageDialog(view, "Tên tài khoản mới đã tồn tại!");
                            return;
                        }
                    }
                }

                // 4. GỌI HÀM UPDATE VỚI 2 THAM SỐ
                // Truyền vào: (Thông tin mới, Tên cũ để tìm dòng cần sửa)
                if (dao.update(tk_Moi, tenTK_Cu)) {
                    JOptionPane.showMessageDialog(view, "Cập nhật thành công");
                    loadTable();
                    view.clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Cập nhật thất bại");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Lỗi cập nhật: " + ex.getMessage());
            }
        });

        // ================== 3. SỰ KIỆN XÓA ==================
        view.btnXoa.addActionListener(e -> {
            try {
                String tenTK = view.getSelectedTenTK();
                if (tenTK == null) {
                    JOptionPane.showMessageDialog(view, "Vui lòng chọn tài khoản để xóa!");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(view,
                        "Xóa tài khoản: " + tenTK + "?",
                        "Xác nhận", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    if (dao.delete(tenTK)) {
                        JOptionPane.showMessageDialog(view, "Xóa thành công");
                        loadTable();
                        view.clearForm();
                    } else {
                        JOptionPane.showMessageDialog(view, "Xóa thất bại");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Lỗi xóa: " + ex.getMessage());
            }
        });

        // ================== 4. SỰ KIỆN CLICK BẢNG ==================
        view.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.fillFormFromTable();
            }
        });

        // ================== 5. SỰ KIỆN TÌM KIẾM ==================
        if (view.btnTim != null) {
            view.btnTim.addActionListener(e -> {
                String keyword = view.txtTim.getText().trim().toLowerCase();

                if (keyword.isEmpty()) {
                    loadTable();
                } else {
                    List<TaiKhoan> allList = dao.getAll();
                    List<TaiKhoan> searchResult = allList.stream()
                            .filter(tk -> tk.getTenTaiKhoan().toLowerCase().contains(keyword))
                            .collect(Collectors.toList());
                    view.showData(searchResult);
                }
            });
        }
    }
}
