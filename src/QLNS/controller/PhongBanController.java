package QLNS.controller;

import QLNS.dao.PhongBanDAO;
import QLNS.model.PhongBan;
import QLNS.view.FrmPhongBan;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class PhongBanController {

    private FrmPhongBan view;
    private PhongBanDAO dao;

    public PhongBanController(FrmPhongBan view) {
        this.view = view;
        try {
            this.dao = new PhongBanDAO();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi kết nối CSDL: " + e.getMessage());
            return;
        }

        loadTable();
        initEvents();
    }

    private void loadTable() {
        List<PhongBan> list = dao.getAll();
        view.showData(list);
    }

    // ===== VALIDATE NGÀY (yyyy-MM-dd) =====
    private boolean checkNgay(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Ngày thành lập không hợp lệ!nVui lòng nhập đúng định dạng: yyyy-MM-dd");
            return false;
        }
    }

    private void initEvents() {
        // --- 1. THÊM ---
        view.btnThem.addActionListener(e -> {
            try {
                PhongBan pb = view.getFormData();

                // Validate
                if (pb.getMaPB().isEmpty() || pb.getTenPB().isEmpty() || pb.getNgayThanhLap().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }

                if (!checkNgay(pb.getNgayThanhLap())) return;

                // Check trùng mã
                for (PhongBan item : dao.getAll()) {
                    if (item.getMaPB().equalsIgnoreCase(pb.getMaPB())) {
                        JOptionPane.showMessageDialog(view, "Mã phòng ban đã tồn tại!");
                        return;
                    }
                }

                if (dao.insert(pb)) {
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

        // --- 2. SỬA ---
        view.btnSua.addActionListener(e -> {
            try {
                String maCu = view.getSelectedMaPB();
                if (maCu == null) {
                    JOptionPane.showMessageDialog(view, "Vui lòng chọn phòng ban cần sửa!");
                    return;
                }

                PhongBan pbMoi = view.getFormData();

                if (pbMoi.getMaPB().isEmpty() || pbMoi.getTenPB().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Thông tin không được để trống!");
                    return;
                }
                if (!checkNgay(pbMoi.getNgayThanhLap())) return;

                // Check trùng mã mới
                if (!pbMoi.getMaPB().equalsIgnoreCase(maCu)) {
                    for (PhongBan item : dao.getAll()) {
                        if (item.getMaPB().equalsIgnoreCase(pbMoi.getMaPB())) {
                            JOptionPane.showMessageDialog(view, "Mã phòng ban đã tồn tại!");
                            return;
                        }
                    }
                }

                if (dao.update(pbMoi, maCu)) {
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

        // --- 3. XÓA ---
        view.btnXoa.addActionListener(e -> {
            try {
                String ma = view.getSelectedMaPB();
                if (ma == null) {
                    JOptionPane.showMessageDialog(view, "Chọn phòng ban cần xóa!");
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(view, "Xóa phòng ban " + ma + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
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

        // --- 4. TÌM KIẾM ---
        if (view.btnTim != null) {
            view.btnTim.addActionListener(e -> {
                String keyword = view.txtTim.getText().trim().toLowerCase();
                if (keyword.isEmpty()) {
                    loadTable();
                } else {
                    List<PhongBan> list = dao.getAll();
                    List<PhongBan> result = list.stream()
                            .filter(pb -> pb.getMaPB().toLowerCase().contains(keyword))
                            .collect(Collectors.toList());
                    view.showData(result);
                }
            });
        }

        // --- 5. CLICK BẢNG ---
        view.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.fillFormFromTable();
            }
        });
    }
}