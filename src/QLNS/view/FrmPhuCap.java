package QLNS.view;

import QLNS.model.PhuCap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmPhuCap extends JPanel {

    public JButton btnThem, btnSua, btnXoa, btnTim, btnLuu;
    public JTable table;
    public JTextField txtTim;
    private JTextField txtMaPC, txtTenPC, txtTienPC, txtNgayHL;

    public FrmPhuCap() {
        initUI();
    }

    // ... (Giữ nguyên initUI) ...
    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel lblWelcome = new JLabel("DANH MỤC PHỤ CẤP", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 15));
        lblWelcome.setForeground(new Color(0, 102, 204));
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 5));

        JPanel pnlInput = new JPanel(new GridLayout(4, 2, 4, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin phụ cấp"));
        txtMaPC = new JTextField();
        txtTenPC = new JTextField();
        txtTienPC = new JTextField();
        txtNgayHL = new JTextField();
        pnlInput.add(new JLabel("Mã phụ cấp:")); pnlInput.add(txtMaPC);
        pnlInput.add(new JLabel("Tên phụ cấp:")); pnlInput.add(txtTenPC);
        pnlInput.add(new JLabel("Tiền phụ cấp:")); pnlInput.add(txtTienPC);
        pnlInput.add(new JLabel("Ngày hiệu lực (yyyy-MM-dd):")); pnlInput.add(txtNgayHL);

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
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Mã PC", "Tên phụ cấp", "Tiền", "Ngày hiệu lực"}));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Danh sách phụ cấp"));
        pnlCenter.add(scroll, BorderLayout.CENTER);
        add(pnlCenter, BorderLayout.CENTER);
    }

    public void showData(List<PhuCap> list) {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Mã PC", "Tên phụ cấp", "Tiền", "Ngày hiệu lực"}, 0);
        for (PhuCap pc : list) {
            model.addRow(new Object[]{pc.getMaPC(), pc.getTenPC(), pc.getTienPC(), pc.getNgayHieuLuc()});
        }
        table.setModel(model);
    }

    public PhuCap getFormData() throws NumberFormatException {
        double tien = 0;
        String tienStr = txtTienPC.getText().trim();
        if (!tienStr.isEmpty()) tien = Double.parseDouble(tienStr);
        return new PhuCap(txtMaPC.getText().trim(), txtTenPC.getText().trim(), tien, txtNgayHL.getText().trim());
    }

    // ===== SỬA ĐỔI TẠI ĐÂY =====
    public void fillFormFromTable() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtMaPC.setText(table.getValueAt(row, 0).toString());
            txtTenPC.setText(table.getValueAt(row, 1).toString());
            txtTienPC.setText(table.getValueAt(row, 2).toString());
            txtNgayHL.setText(table.getValueAt(row, 3).toString());

            // Khóa mã
            txtMaPC.setEnabled(false);
        }
    }

    public String getSelectedMaPC() {
        int row = table.getSelectedRow();
        return row < 0 ? null : table.getValueAt(row, 0).toString();
    }

    public void clearForm() {
        txtMaPC.setText("");
        txtTenPC.setText("");
        txtTienPC.setText("");
        txtNgayHL.setText("");
        table.clearSelection();

        // Mở khóa mã
        txtMaPC.setEnabled(true);
    }
}