package QLNS.controller;

import QLNS.dao.PhuCapDAO;
import QLNS.model.PhuCap;
import QLNS.view.FrmPhuCap;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class PhuCapController {

    private FrmPhuCap view;
    private PhuCapDAO dao;

    public PhuCapController(FrmPhuCap view) {
        this.view = view;
        try {
            this.dao = new PhuCapDAO();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi kết nối CSDL: " + e.getMessage());
            return;
        }

        loadTable();
        initEvents();
    }

    private void loadTable() {
        List<PhuCap> list = dao.getAll();
        view.showData(list);
    }

    // Validate ngày yyyy-MM-dd
    private boolean checkNgay(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Ngày hiệu lực không hợp lệ!\nVui lòng nhập đúng định dạng: yyyy-MM-dd");
            return false;
        }
    }

    private void initEvents() {
        // --- SỰ KIỆN THÊM ---
        view.btnThem.addActionListener(e -> {
            try {
                PhuCap pc = view.getFormData();

                // Validate
                if (pc.getMaPC().isEmpty() || pc.getTenPC().isEmpty() || pc.getNgayHieuLuc().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }

                if (pc.getTienPC() <= 0) {
                    JOptionPane.showMessageDialog(view, "Tiền phụ cấp phải lớn hơn 0!");
                    return;
                }

                if (!checkNgay(pc.getNgayHieuLuc())) return;

                // Check trùng mã
                for (PhuCap item : dao.getAll()) {
                    if (item.getMaPC().equalsIgnoreCase(pc.getMaPC())) {
                        JOptionPane.showMessageDialog(view, "Mã phụ cấp đã tồn tại!");
                        return;
                    }
                }

                if (dao.insert(pc)) {
                    JOptionPane.showMessageDialog(view, "Thêm thành công!");
                    loadTable();
                    view.clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Thêm thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Tiền phụ cấp phải nhập số!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // --- SỰ KIỆN SỬA ---
        view.btnSua.addActionListener(e -> {
            try {
                String maCu = view.getSelectedMaPC();
                if (maCu == null) {
                    JOptionPane.showMessageDialog(view, "Vui lòng chọn phụ cấp cần sửa!");
                    return;
                }

                PhuCap pcMoi = view.getFormData();
                if (pcMoi.getMaPC().isEmpty() || pcMoi.getTenPC().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Thông tin chính không được để trống!");
                    return;
                }
                if (pcMoi.getTienPC() <= 0) {
                    JOptionPane.showMessageDialog(view, "Tiền phụ cấp phải lớn hơn 0!");
                    return;
                }
                if (!checkNgay(pcMoi.getNgayHieuLuc())) return;

                // Check trùng mã mới
                if (!pcMoi.getMaPC().equalsIgnoreCase(maCu)) {
                    for (PhuCap item : dao.getAll()) {
                        if (item.getMaPC().equalsIgnoreCase(pcMoi.getMaPC())) {
                            JOptionPane.showMessageDialog(view, "Mã phụ cấp mới bị trùng!");
                            return;
                        }
                    }
                }

                if (dao.update(pcMoi, maCu)) {
                    JOptionPane.showMessageDialog(view, "Cập nhật thành công!");
                    loadTable();
                    view.clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Cập nhật thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Tiền phụ cấp phải nhập số!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // --- SỰ KIỆN XÓA ---
        view.btnXoa.addActionListener(e -> {
            try {
                String ma = view.getSelectedMaPC();
                if (ma == null) {
                    JOptionPane.showMessageDialog(view, "Vui lòng chọn phụ cấp cần xóa!");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(view,
                        "Xóa phụ cấp " + ma + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);

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
                    List<PhuCap> list = dao.getAll();
                    List<PhuCap> result = list.stream()
                            .filter(pc -> pc.getMaPC().toLowerCase().contains(keyword) ||
                                    pc.getTenPC().toLowerCase().contains(keyword))
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