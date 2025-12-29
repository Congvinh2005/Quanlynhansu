package QLNS.controller;

import QLNS.dao.LuongDAO;
import QLNS.model.Luong;
import QLNS.view.FrmLuong;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class LuongController {

    private FrmLuong view;
    private LuongDAO dao;

    public LuongController(FrmLuong view) {
        this.view = view;
        try {
            this.dao = new LuongDAO();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi kết nối CSDL: " + e.getMessage());
            return;
        }

        loadTable();
        initEvents();
    }

    private void loadTable() {
        List<Luong> list = dao.getAll();
        view.showData(list);
    }

    private void initEvents() {
        // --- SỰ KIỆN THÊM ---
        view.btnThem.addActionListener(e -> {
            try {
                // getFormData có thể ném lỗi nếu nhập lương không phải số
                Luong l = view.getFormData();

                // Validate
                if (l.getMaLuong().isEmpty() || l.getGhiChu().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }
                if (l.getLuongCoBan() <= 0) {
                    JOptionPane.showMessageDialog(view, "Lương cơ bản phải lớn hơn 0!");
                    return;
                }

                // Check trùng mã
                for (Luong item : dao.getAll()) {
                    if (item.getMaLuong().equalsIgnoreCase(l.getMaLuong())) {
                        JOptionPane.showMessageDialog(view, "Mã lương đã tồn tại!");
                        return;
                    }
                }

                if (dao.insert(l)) {
                    JOptionPane.showMessageDialog(view, "Thêm thành công!");
                    loadTable();
                    view.clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Thêm thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Lương cơ bản phải là số!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // --- SỰ KIỆN SỬA ---
        view.btnSua.addActionListener(e -> {
            try {
                String maCu = view.getSelectedMaLuong();
                if (maCu == null) {
                    JOptionPane.showMessageDialog(view, "Vui lòng chọn mức lương cần sửa!");
                    return;
                }

                Luong lMoi = view.getFormData();

                if (lMoi.getMaLuong().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Mã lương không được để trống!");
                    return;
                }
                if (lMoi.getLuongCoBan() <= 0) {
                    JOptionPane.showMessageDialog(view, "Lương cơ bản phải lớn hơn 0!");
                    return;
                }

                // Check trùng mã mới
                if (!lMoi.getMaLuong().equalsIgnoreCase(maCu)) {
                    for (Luong item : dao.getAll()) {
                        if (item.getMaLuong().equalsIgnoreCase(lMoi.getMaLuong())) {
                            JOptionPane.showMessageDialog(view, "Mã lương mới bị trùng!");
                            return;
                        }
                    }
                }

                if (dao.update(lMoi, maCu)) {
                    JOptionPane.showMessageDialog(view, "Cập nhật thành công!");
                    loadTable();
                    view.clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Cập nhật thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Lương cơ bản phải là số!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // --- SỰ KIỆN XÓA ---
        view.btnXoa.addActionListener(e -> {
            try {
                String ma = view.getSelectedMaLuong();
                if (ma == null) {
                    JOptionPane.showMessageDialog(view, "Vui lòng chọn mức lương cần xóa!");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(view, 
                        "Xóa mã lương " + ma + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                
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
                    List<Luong> list = dao.getAll();
                    List<Luong> result = list.stream()
                        .filter(l -> l.getMaLuong().toLowerCase().contains(keyword) || 
                                     String.valueOf(l.getLuongCoBan()).contains(keyword))
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