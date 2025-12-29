package QLNS.controller;

import QLNS.dao.ThuongDAO;
import QLNS.model.Thuong;
import QLNS.view.FrmThuong;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class ThuongController {

    private FrmThuong view;
    private ThuongDAO dao;

    public ThuongController(FrmThuong view) {
        this.view = view;
        try {
            this.dao = new ThuongDAO();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi kết nối CSDL: " + e.getMessage());
            return;
        }

        loadTable();
        initEvents();
    }

    private void loadTable() {
        List<Thuong> list = dao.getAll();
        view.showData(list);
    }

    private void initEvents() {
        // --- SỰ KIỆN THÊM ---
        view.btnThem.addActionListener(e -> {
            try {
                Thuong t = view.getFormData();

                // Validate
                if (t.getMaThuong().isEmpty() || t.getLyDo().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }
                if (t.getSoTien() <= 0) {
                    JOptionPane.showMessageDialog(view, "Số tiền thưởng phải lớn hơn 0!");
                    return;
                }

                // Check trùng mã
                for (Thuong item : dao.getAll()) {
                    if (item.getMaThuong().equalsIgnoreCase(t.getMaThuong())) {
                        JOptionPane.showMessageDialog(view, "Mã thưởng đã tồn tại!");
                        return;
                    }
                }

                if (dao.insert(t)) {
                    JOptionPane.showMessageDialog(view, "Thêm thành công!");
                    loadTable();
                    view.clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Thêm thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Số tiền phải nhập số!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // --- SỰ KIỆN SỬA ---
        view.btnSua.addActionListener(e -> {
            try {
                String maCu = view.getSelectedMaThuong();
                if (maCu == null) {
                    JOptionPane.showMessageDialog(view, "Vui lòng chọn mục thưởng cần sửa!");
                    return;
                }

                Thuong tMoi = view.getFormData();
                if (tMoi.getMaThuong().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Mã thưởng không được để trống!");
                    return;
                }
                if (tMoi.getSoTien() <= 0) {
                    JOptionPane.showMessageDialog(view, "Số tiền thưởng phải lớn hơn 0!");
                    return;
                }

                // Check trùng mã mới
                if (!tMoi.getMaThuong().equalsIgnoreCase(maCu)) {
                    for (Thuong item : dao.getAll()) {
                        if (item.getMaThuong().equalsIgnoreCase(tMoi.getMaThuong())) {
                            JOptionPane.showMessageDialog(view, "Mã thưởng mới bị trùng!");
                            return;
                        }
                    }
                }

                if (dao.update(tMoi, maCu)) {
                    JOptionPane.showMessageDialog(view, "Cập nhật thành công!");
                    loadTable();
                    view.clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Cập nhật thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Số tiền phải nhập số!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // --- SỰ KIỆN XÓA ---
        view.btnXoa.addActionListener(e -> {
            try {
                String ma = view.getSelectedMaThuong();
                if (ma == null) {
                    JOptionPane.showMessageDialog(view, "Vui lòng chọn mục thưởng cần xóa!");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(view,
                        "Xóa mã thưởng " + ma + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);

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
                    List<Thuong> list = dao.getAll();
                    List<Thuong> result = list.stream()
                            .filter(t -> t.getMaThuong().toLowerCase().contains(keyword) ||
                                    t.getLyDo().toLowerCase().contains(keyword))
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