package QLNS.view;

import QLNS.model.Luong;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmLuong extends JPanel {

    public JButton btnThem, btnSua, btnXoa, btnTim, btnLuu;
    public JTable table;
    public JTextField txtTim;

    private JTextField txtMaLuong, txtLuongCoBan, txtGhiChu;

    public FrmLuong() {
        initUI();
    }

    private void initUI() {
        // ... (Giữ nguyên phần giao diện như cũ) ...
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblWelcome = new JLabel("DANH MỤC LƯƠNG", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 15));
        lblWelcome.setForeground(new Color(0, 102, 204));
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 5));

        JPanel pnlInput = new JPanel(new GridLayout(3, 2, 4, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin lương"));

        txtMaLuong = new JTextField();
        txtLuongCoBan = new JTextField();
        txtGhiChu = new JTextField();

        pnlInput.add(new JLabel("Mã lương:")); pnlInput.add(txtMaLuong);
        pnlInput.add(new JLabel("Lương cơ bản:")); pnlInput.add(txtLuongCoBan);
        pnlInput.add(new JLabel("Ghi chú:")); pnlInput.add(txtGhiChu);

        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLuu = new JButton("Lưu");
        pnlBtn.add(btnThem); pnlBtn.add(btnSua); pnlBtn.add(btnXoa); pnlBtn.add(btnLuu);

        JPanel pnlTop = new JPanel(new BorderLayout(5, 5));
        pnlTop.add(pnlInput, BorderLayout.CENTER);
        pnlTop.add(pnlBtn, BorderLayout.SOUTH);

        JPanel pnlNorth = new JPanel(new BorderLayout());
        pnlNorth.add(lblWelcome, BorderLayout.NORTH);
        pnlNorth.add(pnlTop, BorderLayout.CENTER);
        add(pnlNorth, BorderLayout.NORTH);

        JPanel pnlCenter = new JPanel(new BorderLayout(5, 5));
        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 2));
        
        pnlSearch.add(new JLabel("Tìm:"));
        txtTim = new JTextField(15);
        pnlSearch.add(txtTim);
        btnTim = new JButton("Tìm");
        pnlSearch.add(btnTim);
        pnlCenter.add(pnlSearch, BorderLayout.NORTH);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Mã lương", "Lương cơ bản", "Ghi chú"}));
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Danh sách lương"));
        pnlCenter.add(scroll, BorderLayout.CENTER);
        add(pnlCenter, BorderLayout.CENTER);
    }

    public void showData(List<Luong> list) {
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Mã lương", "Lương cơ bản", "Ghi chú"}, 0
        );
        for (Luong l : list) {
            model.addRow(new Object[]{l.getMaLuong(), l.getLuongCoBan(), l.getGhiChu()});
        }
        table.setModel(model);
    }

    public Luong getFormData() throws NumberFormatException {
        double luong = 0;
        String luongStr = txtLuongCoBan.getText().trim();
        if (!luongStr.isEmpty()) {
            luong = Double.parseDouble(luongStr);
        }
        return new Luong(txtMaLuong.getText().trim(), luong, txtGhiChu.getText().trim());
    }

    // ===== PHẦN SỬA ĐỔI CHÍNH =====

    // 1. Khi click vào bảng: Đổ dữ liệu VÀ KHÓA ô Mã Lương
    public void fillFormFromTable() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtMaLuong.setText(table.getValueAt(row, 0).toString());
            txtLuongCoBan.setText(table.getValueAt(row, 1).toString());
            Object ghiChu = table.getValueAt(row, 2);
            txtGhiChu.setText(ghiChu != null ? ghiChu.toString() : "");

            // --- KHÓA MÃ LƯƠNG ---
            txtMaLuong.setEnabled(false);
            // ---------------------
        }
    }

    public String getSelectedMaLuong() {
        int row = table.getSelectedRow();
        return row < 0 ? null : table.getValueAt(row, 0).toString();
    }

    // 2. Khi xóa form (hoặc sau khi Thêm/Sửa/Xóa xong): MỞ KHÓA ô Mã Lương
    public void clearForm() {
        txtMaLuong.setText("");
        txtLuongCoBan.setText("");
        txtGhiChu.setText("");
        table.clearSelection();

        // --- MỞ KHÓA MÃ LƯƠNG ---
        txtMaLuong.setEnabled(true);
        // ------------------------
    }
}