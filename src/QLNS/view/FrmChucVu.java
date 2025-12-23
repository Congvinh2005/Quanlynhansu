package QLNS.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmChucVu extends JPanel {

    // ===== BUTTON =====
    public JButton btnThem, btnLuu, btnXoa, btnTim;

    // ===== TABLE =====
    private JTable table;

    // ===== FIELD =====
    private JTextField txtMaCV, txtTenCV, txtMoTa, txtTim;

    public FrmChucVu() {
        initUI();
        enableForm(false);
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        /* ================= INPUT PANEL ================= */
        JPanel pnlInput = new JPanel(new GridLayout(3, 2, 4, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin chức vụ"));

        txtMaCV = new JTextField();
        txtTenCV = new JTextField();
        txtMoTa = new JTextField();

        pnlInput.add(new JLabel("Mã chức vụ:"));
        pnlInput.add(txtMaCV);

        pnlInput.add(new JLabel("Tên chức vụ:"));
        pnlInput.add(txtTenCV);

        pnlInput.add(new JLabel("Mô tả:"));
        pnlInput.add(txtMoTa);

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
        scroll.setBorder(BorderFactory.createTitledBorder("Danh sách chức vụ"));

        pnlCenter.add(scroll, BorderLayout.CENTER);
        add(pnlCenter, BorderLayout.CENTER);
    }

    /* ================= DATA ================= */
    public void showData(List<Object[]> list) {
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Mã CV", "Tên chức vụ", "Mô tả"}, 0
        );

        for (Object[] row : list) {
            model.addRow(row);
        }
        table.setModel(model);
    }

    public Object[] getFormData() {
        return new Object[]{
                txtMaCV.getText().trim(),
                txtTenCV.getText().trim(),
                txtMoTa.getText().trim()
        };
    }

    public void fillFormFromTable() {
        int row = table.getSelectedRow();
        if (row < 0) return;

        txtMaCV.setText(table.getValueAt(row, 0).toString());
        txtTenCV.setText(table.getValueAt(row, 1).toString());
        txtMoTa.setText(table.getValueAt(row, 2).toString());

        enableForm(true);
    }

    public void clearForm() {
        txtMaCV.setText("");
        txtTenCV.setText("");
        txtMoTa.setText("");
    }

    public String getKeyword() {
        return txtTim.getText().trim();
    }

    public String getSelectedMaCV() {
        int row = table.getSelectedRow();
        return row < 0 ? null : table.getValueAt(row, 0).toString();
    }

    public void enableForm(boolean b) {
        txtMaCV.setEnabled(b);
        txtTenCV.setEnabled(b);
        txtMoTa.setEnabled(b);
    }

    public JTable getTable() {
        return table;
    }
}
