package QLNS.controller;

import QLNS.dao.NhanVienDAO;
import QLNS.model.NhanVien;
import QLNS.view.FrmQLTK;
import QLNS.view.FrmThongTinCaNhan;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class NhanVienController {

    private FrmThongTinCaNhan viewNV;
    private FrmQLTK viewTK;
    private NhanVienDAO dao;

    // ... (Giữ nguyên các Constructor) ...
    public NhanVienController(FrmThongTinCaNhan view) {
        this.viewNV = view;
        try { this.dao = new NhanVienDAO(); } 
        catch (Exception e) { JOptionPane.showMessageDialog(view, "Lỗi kết nối: " + e.getMessage()); return; }
        loadTable();
        initEvents();
    }

    public NhanVienController(FrmQLTK view) {
        this.viewTK = view;
        try { this.dao = new NhanVienDAO(); } 
        catch (Exception e) { return; }
        loadComboBoxMaNV();
    }

    private void loadTable() {
        if (viewNV != null) {
            List<NhanVien> list = dao.getAll();
            viewNV.showData(list);
        }
    }

    // ===== 1. SỬA LẠI: CHECK NGÀY SINH THEO CHUẨN SQL (yyyy-MM-dd) =====
    private boolean checkNgaySinh(String dateStr) {
        // Đổi format sang Năm-Tháng-Ngày
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); 

        try {
            Date date = sdf.parse(dateStr);
            Date now = new Date();

            if (date.after(now)) {
                JOptionPane.showMessageDialog(viewNV, "Ngày sinh không được lớn hơn ngày hiện tại!");
                return false;
            }

            // Cắt chuỗi lấy năm để kiểm tra (yyyy-MM-dd -> Năm nằm ở đầu, từ index 0 đến 4)
            if (dateStr.length() == 10) {
                 int year = Integer.parseInt(dateStr.substring(0, 4)); // Lấy 4 ký tự đầu
                 if (year < 1900) {
                     JOptionPane.showMessageDialog(viewNV, "Năm sinh phải lớn hơn hoặc bằng 1900!");
                     return false;
                 }
            }
            return true;
        } catch (ParseException e) {
            // Thông báo bắt buộc nhập đúng định dạng
            JOptionPane.showMessageDialog(viewNV, "Ngày sinh không hợp lệ!\nVui lòng nhập đúng định dạng: yyyy-MM-dd");
            return false;
        }
    }

    // ===== 2. CHECK SĐT (Giữ nguyên) =====
    private boolean checkSDT(String sdt) {
        if (!sdt.matches("\\d+")) {
            JOptionPane.showMessageDialog(viewNV, "Số điện thoại chỉ được chứa ký tự số!");
            return false;
        }
        return true;
    }

    private void initEvents() {
        // --- SỰ KIỆN THÊM ---
        viewNV.btnThem.addActionListener(e -> {
            try {
                NhanVien nv = viewNV.getFormData();

                // Validate
                if (nv.getMaNV().isEmpty() || nv.getHoTen().isEmpty() || nv.getNgaySinh().isEmpty() || nv.getSdt().isEmpty()) {
                    JOptionPane.showMessageDialog(viewNV, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }

                // Gọi hàm check ngày sinh (yyyy-MM-dd)
                if (!checkNgaySinh(nv.getNgaySinh())) return;
                
                // Gọi hàm check SĐT
                if (!checkSDT(nv.getSdt())) return;

                // Check trùng mã
                List<NhanVien> list = dao.getAll();
                for (NhanVien existing : list) {
                    if (existing.getMaNV().equalsIgnoreCase(nv.getMaNV())) {
                        JOptionPane.showMessageDialog(viewNV, "Mã nhân viên đã tồn tại!");
                        return;
                    }
                }

                // Insert thẳng vào DB (vì input đã chuẩn yyyy-MM-dd rồi)
                if (dao.insert(nv)) {
                    JOptionPane.showMessageDialog(viewNV, "Thêm thành công!");
                    loadTable();
                    viewNV.clearForm();
                } else {
                    JOptionPane.showMessageDialog(viewNV, "Thêm thất bại!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(viewNV, "Lỗi thêm: " + ex.getMessage());
            }
        });

        // --- SỰ KIỆN SỬA ---
        viewNV.btnSua.addActionListener(e -> {
            try {
                String maNV_Cu = viewNV.getSelectedMaNV();
                if (maNV_Cu == null) {
                    JOptionPane.showMessageDialog(viewNV, "Vui lòng chọn nhân viên cần sửa!");
                    return;
                }

                NhanVien nv_Moi = viewNV.getFormData();
                
                if (nv_Moi.getMaNV().isEmpty() || nv_Moi.getHoTen().isEmpty()) {
                    JOptionPane.showMessageDialog(viewNV, "Thông tin chính không được để trống!");
                    return;
                }

                if (!checkNgaySinh(nv_Moi.getNgaySinh())) return;
                if (!checkSDT(nv_Moi.getSdt())) return;

                if (!nv_Moi.getMaNV().equalsIgnoreCase(maNV_Cu)) {
                    List<NhanVien> list = dao.getAll();
                    for (NhanVien existing : list) {
                        if (existing.getMaNV().equalsIgnoreCase(nv_Moi.getMaNV())) {
                            JOptionPane.showMessageDialog(viewNV, "Mã nhân viên mới đã tồn tại!");
                            return;
                        }
                    }
                }

                if (dao.update(nv_Moi, maNV_Cu)) {
                    JOptionPane.showMessageDialog(viewNV, "Cập nhật thành công!");
                    loadTable();
                    viewNV.clearForm();
                } else {
                    JOptionPane.showMessageDialog(viewNV, "Cập nhật thất bại!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(viewNV, "Lỗi sửa: " + ex.getMessage());
            }
        });

        // --- SỰ KIỆN XÓA (Giữ nguyên) ---
        viewNV.btnXoa.addActionListener(e -> {
            try {
                String maNV = viewNV.getSelectedMaNV();
                if (maNV == null) {
                    JOptionPane.showMessageDialog(viewNV, "Vui lòng chọn nhân viên cần xóa!");
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(viewNV, "Xóa nhân viên: " + maNV + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (dao.delete(maNV)) {
                        JOptionPane.showMessageDialog(viewNV, "Xóa thành công!");
                        loadTable();
                        viewNV.clearForm();
                    } else {
                        JOptionPane.showMessageDialog(viewNV, "Xóa thất bại!");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(viewNV, "Lỗi xóa: " + ex.getMessage());
            }
        });

        // --- SỰ KIỆN TÌM KIẾM (Giữ nguyên) ---
        if (viewNV.btnTim != null) {
            viewNV.btnTim.addActionListener(e -> {
                String keyword = viewNV.txtTim.getText().trim().toLowerCase();
                if (keyword.isEmpty()) {
                    loadTable();
                } else {
                    List<NhanVien> all = dao.getAll();
                    List<NhanVien> res = all.stream()
                        .filter(nv -> nv.getMaNV().toLowerCase().contains(keyword))
                        .collect(java.util.stream.Collectors.toList());
                    viewNV.showData(res);
                }
            });
        }

        viewNV.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                viewNV.fillFormFromTable();
            }
        });
    }

    public void loadComboBoxMaNV() {
        if (viewTK != null) {
            try {
                List<String> listMa = dao.getAllMaNV();
                viewTK.getCboMaNV().removeAllItems();
                for (String ma : listMa) viewTK.getCboMaNV().addItem(ma);
            } catch (Exception e) {}
        }
    }
}