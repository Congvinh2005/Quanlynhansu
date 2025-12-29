package QLNS.view;

import QLNS.model.Thuong;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmThuong extends JPanel {

    public JButton btnThem, btnSua, btnXoa, btnTim, btnLuu;
    public JTable table;
    public JTextField txtTim;
    private JTextField txtMaThuong, txtSoTien, txtLyDo;

    public FrmThuong() {
        initUI();
    }
    
    // ... (Giữ nguyên initUI) ...
    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel lblWelcome = new JLabel("DANH MỤC THƯỞNG", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 15));
        lblWelcome.setForeground(new Color(0, 102, 204));
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 5));

        JPanel pnlInput = new JPanel(new GridLayout(3, 2, 4, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin thưởng"));
        txtMaThuong = new JTextField();
        txtSoTien = new JTextField();
        txtLyDo = new JTextField();
        pnlInput.add(new JLabel("Mã thưởng:")); pnlInput.add(txtMaThuong);
        pnlInput.add(new JLabel("Số tiền:")); pnlInput.add(txtSoTien);
        pnlInput.add(new JLabel("Lý do:")); pnlInput.add(txtLyDo);

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
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Mã thưởng", "Số tiền", "Lý do"}));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Danh sách thưởng"));
        pnlCenter.add(scroll, BorderLayout.CENTER);
        add(pnlCenter, BorderLayout.CENTER);
    }

    public void showData(List<Thuong> list) {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Mã thưởng", "Số tiền", "Lý do"}, 0);
        for (Thuong t : list) {
            model.addRow(new Object[]{t.getMaThuong(), t.getSoTien(), t.getLyDo()});
        }
        table.setModel(model);
    }

    public Thuong getFormData() throws NumberFormatException {
        double soTien = 0;
        String tienStr = txtSoTien.getText().trim();
        if (!tienStr.isEmpty()) soTien = Double.parseDouble(tienStr);
        return new Thuong(txtMaThuong.getText().trim(), soTien, txtLyDo.getText().trim());
    }

    // ===== SỬA ĐỔI TẠI ĐÂY =====
    public void fillFormFromTable() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtMaThuong.setText(table.getValueAt(row, 0).toString());
            txtSoTien.setText(table.getValueAt(row, 1).toString());
            Object lyDo = table.getValueAt(row, 2);
            txtLyDo.setText(lyDo != null ? lyDo.toString() : "");

            // Khóa mã
            txtMaThuong.setEnabled(false);
        }
    }

    public String getSelectedMaThuong() {
        int row = table.getSelectedRow();
        return row < 0 ? null : table.getValueAt(row, 0).toString();
    }

    public void clearForm() {
        txtMaThuong.setText("");
        txtSoTien.setText("");
        txtLyDo.setText("");
        table.clearSelection();

        // Mở khóa mã
        txtMaThuong.setEnabled(true);
    }
}