package QLNS.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmLuong extends JPanel {

    // ===== BUTTON =====
    public JButton btnThem, btnLuu, btnXoa, btnTim;

    // ===== TABLE =====
    private JTable table;

    // ===== FIELD =====
    private JTextField txtMaLuong, txtLuongCoBan, txtGhiChu, txtTim;

    public FrmLuong() {
        initUI();
        enableForm(false);
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        /* ================= INPUT PANEL ================= */
        JPanel pnlInput = new JPanel(new GridLayout(3, 2, 4, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin lương"));

        txtMaLuong = new JTextField();
        txtLuongCoBan = new JTextField();
        txtGhiChu = new JTextField();

        pnlInput.add(new JLabel("Mã lương:"));
        pnlInput.add(txtMaLuong);

        pnlInput.add(new JLabel("Lương cơ bản:"));
        pnlInput.add(txtLuongCoBan);

        pnlInput.add(new JLabel("Ghi chú:"));
        pnlInput.add(txtGhiChu);

        /* ================= BUTTON PANEL ================= */
        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));

        btnThem = new JButton("Thêm");
        btnLuu = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        pnlBtn.add(btnThem);
        pnlBtn.add(btnLuu);
        pnlBtn.add(btnXoa);

        JPanel pnlTop = new JPanel(new BorderLayout(5, 5));
        pnlTop.add(pnlInput, BorderLayout.CENTER);
        pnlTop.add(pnlBtn, BorderLayout.SOUTH);

        add(pnlTop, BorderLayout.NORTH);

        /* ================= CENTER ================= */
        JPanel pnlCenter = new JPanel(new BorderLayout(5, 5));

        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 2));
        pnlSearch.add(new JLabel("Tìm:"));

        txtTim = new JTextField(15);
        pnlSearch.add(txtTim);

        btnTim = new JButton("Tìm");
        pnlSearch.add(btnTim);

        pnlCenter.add(pnlSearch, BorderLayout.NORTH);

        table = new JTable();
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Danh sách lương"));

        pnlCenter.add(scroll, BorderLayout.CENTER);
        add(pnlCenter, BorderLayout.CENTER);
    }

    /* ================= DATA ================= */
    public void showData(List<Object[]> list) {
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Mã lương", "Lương cơ bản", "Ghi chú"}, 0
        );

        for (Object[] row : list) {
            model.addRow(row);
        }
        table.setModel(model);
    }

    public Object[] getFormData() {
        return new Object[]{
                txtMaLuong.getText().trim(),
                txtLuongCoBan.getText().trim(),
                txtGhiChu.getText().trim()
        };
    }

    public void fillFormFromTable() {
        int row = table.getSelectedRow();
        if (row < 0) return;

        txtMaLuong.setText(table.getValueAt(row, 0).toString());
        txtLuongCoBan.setText(table.getValueAt(row, 1).toString());
        txtGhiChu.setText(table.getValueAt(row, 2).toString());

        enableForm(true);
    }

    public void clearForm() {
        txtMaLuong.setText("");
        txtLuongCoBan.setText("");
        txtGhiChu.setText("");
    }

    public String getKeyword() {
        return txtTim.getText().trim();
    }

    public String getSelectedMaLuong() {
        int row = table.getSelectedRow();
        return row < 0 ? null : table.getValueAt(row, 0).toString();
    }

    public void enableForm(boolean b) {
        txtMaLuong.setEnabled(b);
        txtLuongCoBan.setEnabled(b);
        txtGhiChu.setEnabled(b);
    }

    public JTable getTable() {
        return table;
    }
}
