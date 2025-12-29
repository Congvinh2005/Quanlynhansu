package QLNS.controller;

import QLNS.dao.ChucVuDAO;
import QLNS.model.ChucVu;
import QLNS.view.FrmChucVu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class ChucVuController {

    private FrmChucVu view;
    private ChucVuDAO dao;

    public ChucVuController(FrmChucVu view) {
        this.view = view;
        try {
            this.dao = new ChucVuDAO();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi kết nối CSDL: " + e.getMessage());
            return;
        }

        loadTable();
        initEvents();
    }

    private void loadTable() {
        List<ChucVu> list = dao.getAll();
        view.showData(list);
    }

    private void initEvents() {
        // --- SỰ KIỆN THÊM ---
        view.btnThem.addActionListener(e -> {
            try {
                ChucVu cv = view.getFormData();

                if (cv.getMaCV().isEmpty() || cv.getTenCV().isEmpty() || cv.getMoTa().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }

                // Check trùng mã
                for (ChucVu item : dao.getAll()) {
                    if (item.getMaCV().equalsIgnoreCase(cv.getMaCV())) {
                        JOptionPane.showMessageDialog(view, "Mã chức vụ đã tồn tại!");
                        return;
                    }
                }

                if (dao.insert(cv)) {
                    JOptionPane.showMessageDialog(view, "Thêm thành công!");
                    loadTable();
                    view.clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Thêm thất bại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // --- SỰ KIỆN SỬA ---
        view.btnSua.addActionListener(e -> {
            try {
                String maCu = view.getSelectedMaCV();
                if (maCu == null) {
                    JOptionPane.showMessageDialog(view, "Vui lòng chọn chức vụ cần sửa!");
                    return;
                }

                ChucVu cvMoi = view.getFormData();
                if (cvMoi.getMaCV().isEmpty() || cvMoi.getTenCV().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Thông tin không được để trống!");
                    return;
                }

                // Check trùng mã nếu đổi mã
                if (!cvMoi.getMaCV().equalsIgnoreCase(maCu)) {
                    for (ChucVu item : dao.getAll()) {
                        if (item.getMaCV().equalsIgnoreCase(cvMoi.getMaCV())) {
                            JOptionPane.showMessageDialog(view, "Mã mới bị trùng!");
                            return;
                        }
                    }
                }

                if (dao.update(cvMoi, maCu)) {
                    JOptionPane.showMessageDialog(view, "Cập nhật thành công!");
                    loadTable();
                    view.clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Cập nhật thất bại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // --- SỰ KIỆN XÓA ---
        view.btnXoa.addActionListener(e -> {
            try {
                String ma = view.getSelectedMaCV();
                if (ma == null) {
                    JOptionPane.showMessageDialog(view, "Vui lòng chọn chức vụ cần xóa!");
                    return;
                }
                
                int confirm = JOptionPane.showConfirmDialog(view, 
                        "Xóa chức vụ " + ma + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    if (dao.delete(ma)) {
                        JOptionPane.showMessageDialog(view, "Xóa thành công!");
                        loadTable();
                        view.clearForm();
                    } else {
                        JOptionPane.showMessageDialog(view, "Xóa thất bại!");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // --- SỰ KIỆN TÌM KIẾM ---
        if (view.btnTim != null) {
            view.btnTim.addActionListener(e -> {
                String keyword = view.txtTim.getText().trim().toLowerCase();
                if (keyword.isEmpty()) {
                    loadTable();
                } else {
                    List<ChucVu> list = dao.getAll();
                    List<ChucVu> result = list.stream()
                        .filter(cv -> cv.getTenCV().toLowerCase().contains(keyword) || 
                                      cv.getMaCV().toLowerCase().contains(keyword))
                        .collect(Collectors.toList());
                    view.showData(result);
                }
            });
        }

        // --- SỰ KIỆN CLICK BẢNG ---
        view.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.fillFormFromTable();
            }
        });
    }
}