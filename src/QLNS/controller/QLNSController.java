package QLNS.controller;

import QLNS.dao.ChucVuDAO;
import QLNS.dao.NhanVienDAO;
import QLNS.dao.PhongBanDAO;
import QLNS.model.ChucVu;
import QLNS.model.PhongBan;
import QLNS.view.FrmQLNS;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class QLNSController {

    private FrmQLNS view;
    private NhanVienDAO daoNV;
    private PhongBanDAO daoPB;
    private ChucVuDAO daoCV;

    // Lưu danh sách để tra cứu ngược từ Tên -> Mã
    private List<PhongBan> listPB;
    private List<ChucVu> listCV;

    public QLNSController(FrmQLNS view) {
        this.view = view;
        try {
            daoNV = new NhanVienDAO();
            daoPB = new PhongBanDAO();
            daoCV = new ChucVuDAO();
        } catch (Exception e) { e.printStackTrace(); }

        loadData();
        initEvents();
    }

    private void loadData() {
        // 1. Load ComboBox
        listPB = daoPB.getAll();
        listCV = daoCV.getAll();
        view.loadCombos(listPB, listCV);

        // 2. Load Table
        loadTable();
    }

    private void loadTable() {
        List<Object[]> list = daoNV.getAllHienThi(); // Hàm mới viết trong DAO
        view.showData(list);
    }

    // Hàm tìm Mã PB dựa theo Tên PB (từ ComboBox)
    private String findMaPB(String tenPB) {
        if (tenPB == null) return null;
        for (PhongBan pb : listPB) {
            if (pb.getTenPB().equals(tenPB)) return pb.getMaPB();
        }
        return null;
    }

    // Hàm tìm Mã CV dựa theo Tên CV (từ ComboBox)
    private String findMaCV(String tenCV) {
        if (tenCV == null) return null;
        for (ChucVu cv : listCV) {
            if (cv.getTenCV().equals(tenCV)) return cv.getMaCV();
        }
        return null;
    }

    private void initEvents() {
        // 1. CLICK BẢNG
        view.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.fillForm();
            }
        });

        // 2. PHÂN CÔNG (Lưu)
        view.btnPhanCong.addActionListener(e -> {
            String maNV = view.getSelectedMaNV();
            if (maNV.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn nhân viên để phân công!");
                return;
            }

            // Lấy tên từ combo -> Tìm ra mã ID tương ứng
            String tenPB = view.getSelectedTenPB();
            String tenCV = view.getSelectedTenCV();

            String maPB = findMaPB(tenPB);
            String maCV = findMaCV(tenCV);

            // Gọi DAO cập nhật
            if (daoNV.phanCong(maNV, maPB, maCV)) {
                JOptionPane.showMessageDialog(view, "Phân công thành công!");
                loadTable();
                view.clearForm();
            } else {
                JOptionPane.showMessageDialog(view, "Phân công thất bại!");
            }
        });

        // 3. XÓA (Xóa nhân viên khỏi hệ thống)
        view.btnXoa.addActionListener(e -> {
            String maNV = view.getSelectedMaNV();
            if (maNV.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Chọn nhân viên cần xóa!");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(view, "Xóa nhân viên " + maNV + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (daoNV.delete(maNV)) { // Hàm delete cũ
                    JOptionPane.showMessageDialog(view, "Xóa thành công!");
                    loadTable();
                    view.clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Xóa thất bại!");
                }
            }
        });

        // 4. LÀM MỚI
        view.btnLuu.addActionListener(e -> {
            view.clearForm();
            loadTable();
        });

        // 5. TÌM KIẾM
        view.btnTim.addActionListener(e -> {
            String tuKhoa = view.txtTim.getText().trim().toLowerCase();
            List<Object[]> list = daoNV.getAllHienThi();
            List<Object[]> ketQua = list.stream()
                    .filter(row -> row[0].toString().toLowerCase().contains(tuKhoa) || // Mã
                            row[1].toString().toLowerCase().contains(tuKhoa))   // Tên
                    .collect(Collectors.toList());
            view.showData(ketQua);
        });
    }
}