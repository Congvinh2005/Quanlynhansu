package QLNS.view;

import QLNS.model.PhongBan;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmPhongBan extends JPanel {

    public JButton btnThem, btnSua, btnXoa, btnTim, btnLuu;
    public JTable table;
    public JTextField txtTim;
    private JTextField txtMaPB, txtTenPB, txtNgayThanhLap, txtGhiChu;

    public FrmPhongBan() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel lblWelcome = new JLabel("DANH MỤC PHÒNG BAN", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 15));
        lblWelcome.setForeground(new Color(0, 102, 204));
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 5));

        JPanel pnlInput = new JPanel(new GridLayout(4, 2, 6, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin phòng ban"));

        txtMaPB = new JTextField();
        txtTenPB = new JTextField();
        txtNgayThanhLap = new JTextField();
        txtGhiChu = new JTextField();

        pnlInput.add(new JLabel("Mã phòng ban:")); pnlInput.add(txtMaPB);
        pnlInput.add(new JLabel("Tên phòng ban:")); pnlInput.add(txtTenPB);
        pnlInput.add(new JLabel("Ngày thành lập:")); pnlInput.add(txtNgayThanhLap);
        pnlInput.add(new JLabel("Ghi chú:")); pnlInput.add(txtGhiChu);

        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        btnThem = new JButton("Thêm"); btnSua = new JButton("Sửa"); btnXoa = new JButton("Xóa"); btnLuu = new JButton("Lưu");
        pnlBtn.add(btnThem); pnlBtn.add(btnSua); pnlBtn.add(btnXoa); pnlBtn.add(btnLuu);

        JPanel pnlTop = new JPanel(new BorderLayout(5, 5));
        pnlTop.add(pnlInput, BorderLayout.CENTER); pnlTop.add(pnlBtn, BorderLayout.SOUTH);
        JPanel pnlNorth = new JPanel(new BorderLayout());
        pnlNorth.add(lblWelcome, BorderLayout.NORTH); pnlNorth.add(pnlTop, BorderLayout.CENTER);
        add(pnlNorth, BorderLayout.NORTH);

        JPanel pnlCenter = new JPanel(new BorderLayout(5, 5));
        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        pnlSearch.add(new JLabel("Tìm:"));
        txtTim = new JTextField(15); pnlSearch.add(txtTim);
        btnTim = new JButton("Tìm"); pnlSearch.add(btnTim);
        pnlCenter.add(pnlSearch, BorderLayout.NORTH);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Mã PB", "Tên PB", "Ngày TL", "Ghi chú"}));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Danh sách phòng ban"));
        pnlCenter.add(scroll, BorderLayout.CENTER);
        add(pnlCenter, BorderLayout.CENTER);
    }

    public void showData(List<PhongBan> list) {
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Mã PB", "Tên phòng ban", "Ngày thành lập", "Ghi chú"}, 0
        );
        for (PhongBan pb : list) {
            model.addRow(new Object[]{
                    pb.getMaPB(), pb.getTenPB(), pb.getNgayThanhLap(), pb.getGhiChu()
            });
        }
        table.setModel(model);
    }

    public PhongBan getFormData() {
        return new PhongBan(
                txtMaPB.getText().trim(),
                txtTenPB.getText().trim(),
                txtNgayThanhLap.getText().trim(),
                txtGhiChu.getText().trim()
        );
    }

    // --- SỬA Ở ĐÂY: KHÓA MÃ KHI CLICK BẢNG ---
    public void fillFormFromTable() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtMaPB.setText(table.getValueAt(row, 0).toString());
            txtTenPB.setText(table.getValueAt(row, 1).toString());
            txtNgayThanhLap.setText(table.getValueAt(row, 2).toString());
            Object ghiChu = table.getValueAt(row, 3);
            txtGhiChu.setText(ghiChu != null ? ghiChu.toString() : "");

            // ==> KHÓA MÃ
            txtMaPB.setEnabled(false);
        }
    }

    public String getSelectedMaPB() {
        int row = table.getSelectedRow();
        return row < 0 ? null : table.getValueAt(row, 0).toString();
    }

    // --- SỬA Ở ĐÂY: MỞ KHÓA MÃ KHI CLEAR FORM ---
    public void clearForm() {
        txtMaPB.setText("");
        txtTenPB.setText("");
        txtNgayThanhLap.setText("");
        txtGhiChu.setText("");
        table.clearSelection();

        // ==> MỞ KHÓA MÃ
        txtMaPB.setEnabled(true);
    }
}