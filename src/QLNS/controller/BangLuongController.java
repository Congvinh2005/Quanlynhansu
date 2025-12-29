package QLNS.controller;

import QLNS.dao.BangLuongDAO;
import QLNS.dao.LuongDAO;
import QLNS.dao.PhuCapDAO;
import QLNS.dao.ThuongDAO;
import QLNS.model.Luong;
import QLNS.model.PhuCap;
import QLNS.model.Thuong;
import QLNS.view.FrmBangLuong;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;

public class BangLuongController {

    private FrmBangLuong view;
    private BangLuongDAO daoBL;
    private LuongDAO daoLuong;
    private ThuongDAO daoThuong;
    private PhuCapDAO daoPC;

    public BangLuongController(FrmBangLuong view) {
        this.view = view;
        try {
            daoBL = new BangLuongDAO();
            daoLuong = new LuongDAO();
            daoThuong = new ThuongDAO();
            daoPC = new PhuCapDAO();
        } catch (Exception e) { e.printStackTrace(); }

        loadData();
        initEvents();
    }

    private void loadData() {
        // 1. Load Combos
        List<Luong> listL = daoLuong.getAll();
        List<Thuong> listT = daoThuong.getAll();
        List<PhuCap> listP = daoPC.getAll();
        view.loadCombos(listL, listT, listP);

        // 2. Load Table
        loadTable();
    }

    private void loadTable() {
        List<Object[]> list = daoBL.getAllBangLuong();
        view.showData(list);
    }

    private void initEvents() {
        // 1. Click Bảng
        view.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.fillForm();
                // Khi click bảng xong, gọi hàm tính toán lại để khớp hiển thị
                view.updateThucLinhDisplay();
            }
        });

        // 2. Sự kiện thay đổi ComboBox -> Tự động tính lương thực lĩnh
        ActionListener comboAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.updateThucLinhDisplay();
            }
        };
        view.getCboLuongCoBan().addActionListener(comboAction);
        view.getCboTienThuong().addActionListener(comboAction);
        view.getCboTienPhuCap().addActionListener(comboAction);

        // 3. Nút Cập Nhật
        view.btnCapNhat.addActionListener(e -> {
            String maNV = view.getSelectedMaNV();
            if (maNV.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn nhân viên để tính lương!");
                return;
            }

            String maL = view.getSelectedMaLuong();
            String maT = view.getSelectedMaThuong();
            String maP = view.getSelectedMaPhuCap();

            if (daoBL.updateBangLuong(maNV, maL, maT, maP)) {
                JOptionPane.showMessageDialog(view, "Cập nhật bảng lương thành công!");
                loadTable();
                view.clearForm();
            } else {
                JOptionPane.showMessageDialog(view, "Cập nhật thất bại!");
            }
        });

        // 4. Nút Làm mới
        view.btnLuu.addActionListener(e -> {
            view.clearForm();
            loadTable();
        });
        
        // 5. Nút Xóa (Set các mã về null -> Lương = 0)
        view.btnXoa.addActionListener(e -> {
            String maNV = view.getSelectedMaNV();
            if (maNV.isEmpty()) return;
            
            int cf = JOptionPane.showConfirmDialog(view, "Xóa thông tin lương của nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (cf == JOptionPane.YES_OPTION) {
                // Xóa lương tức là set mã lương/thưởng/phụ cấp về null
                if (daoBL.updateBangLuong(maNV, null, null, null)) {
                    JOptionPane.showMessageDialog(view, "Đã xóa thông tin lương!");
                    loadTable();
                    view.clearForm();
                }
            }
        });
    }
}