package QLNS.view;

import QLNS.model.ChucVu;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmChucVu extends JPanel {

    public JButton btnThem, btnSua, btnXoa, btnTim, btnLuu;
    public JTable table;
    public JTextField txtTim;
    private JTextField txtMaCV, txtTenCV, txtMoTa;

    public FrmChucVu() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblWelcome = new JLabel("DANH MỤC CHỨC VỤ", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 15));
        lblWelcome.setForeground(new Color(0, 102, 204));
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 5));

        JPanel pnlInput = new JPanel(new GridLayout(3, 2, 4, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin chức vụ"));

        txtMaCV = new JTextField();
        txtTenCV = new JTextField();
        txtMoTa = new JTextField();

        pnlInput.add(new JLabel("Mã chức vụ:")); pnlInput.add(txtMaCV);
        pnlInput.add(new JLabel("Tên chức vụ:")); pnlInput.add(txtTenCV);
        pnlInput.add(new JLabel("Mô tả:")); pnlInput.add(txtMoTa);

        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        btnThem = new JButton("Thêm"); btnSua = new JButton("Sửa"); btnXoa = new JButton("Xóa"); btnLuu = new JButton("Lưu");
        pnlBtn.add(btnThem); pnlBtn.add(btnSua); pnlBtn.add(btnXoa); pnlBtn.add(btnLuu);

        JPanel pnlTop = new JPanel(new BorderLayout(5, 5));
        pnlTop.add(pnlInput, BorderLayout.CENTER); pnlTop.add(pnlBtn, BorderLayout.SOUTH);
        JPanel pnlNorth = new JPanel(new BorderLayout());
        pnlNorth.add(lblWelcome, BorderLayout.NORTH); pnlNorth.add(pnlTop, BorderLayout.CENTER);
        add(pnlNorth, BorderLayout.NORTH);

        JPanel pnlCenter = new JPanel(new BorderLayout(5, 5));
        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 2));
        pnlSearch.add(new JLabel("Tìm:"));
        txtTim = new JTextField(15); pnlSearch.add(txtTim);
        btnTim = new JButton("Tìm"); pnlSearch.add(btnTim);
        pnlCenter.add(pnlSearch, BorderLayout.NORTH);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Mã CV", "Tên chức vụ", "Mô tả"}));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Danh sách chức vụ"));
        pnlCenter.add(scroll, BorderLayout.CENTER);
        add(pnlCenter, BorderLayout.CENTER);
    }

    public void showData(List<ChucVu> list) {
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Mã CV", "Tên chức vụ", "Mô tả"}, 0
        );
        for (ChucVu cv : list) {
            model.addRow(new Object[]{cv.getMaCV(), cv.getTenCV(), cv.getMoTa()});
        }
        table.setModel(model);
    }

    public ChucVu getFormData() {
        return new ChucVu(
                txtMaCV.getText().trim(),
                txtTenCV.getText().trim(),
                txtMoTa.getText().trim()
        );
    }

    // --- SỬA Ở ĐÂY: KHÓA MÃ KHI CLICK BẢNG ---
    public void fillFormFromTable() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtMaCV.setText(table.getValueAt(row, 0).toString());
            txtTenCV.setText(table.getValueAt(row, 1).toString());
            Object moTa = table.getValueAt(row, 2);
            txtMoTa.setText(moTa != null ? moTa.toString() : "");

            // ==> KHÓA MÃ
            txtMaCV.setEnabled(false);
        }
    }

    public String getSelectedMaCV() {
        int row = table.getSelectedRow();
        return row < 0 ? null : table.getValueAt(row, 0).toString();
    }

    // --- SỬA Ở ĐÂY: MỞ KHÓA MÃ KHI CLEAR FORM ---
    public void clearForm() {
        txtMaCV.setText("");
        txtTenCV.setText("");
        txtMoTa.setText("");
        table.clearSelection();

        // ==> MỞ KHÓA MÃ
        txtMaCV.setEnabled(true);
    }
}