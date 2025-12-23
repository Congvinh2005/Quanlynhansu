package QLNS.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmThuong extends JPanel {

    public JButton btnThem, btnLuu, btnXoa, btnTim;
    private JTable table;
    private JTextField txtMaThuong, txtSoTien, txtLyDo, txtTim;

    public FrmThuong() {
        initUI();
        enableForm(false);
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        /* ===== INPUT ===== */
        JPanel pnlInput = new JPanel(new GridLayout(3, 2, 4, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin thưởng"));

        txtMaThuong = new JTextField();
        txtSoTien = new JTextField();
        txtLyDo = new JTextField();

        pnlInput.add(new JLabel("Mã thưởng:"));
        pnlInput.add(txtMaThuong);

        pnlInput.add(new JLabel("Số tiền:"));
        pnlInput.add(txtSoTien);

        pnlInput.add(new JLabel("Lý do:"));
        pnlInput.add(txtLyDo);

        /* ===== BUTTON ===== */
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

        /* ===== CENTER ===== */
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
        scroll.setBorder(BorderFactory.createTitledBorder("Danh sách thưởng"));
        pnlCenter.add(scroll, BorderLayout.CENTER);

        add(pnlCenter, BorderLayout.CENTER);
    }

    public void showData(List<Object[]> list) {
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Mã thưởng", "Số tiền", "Lý do"}, 0
        );
        for (Object[] row : list) model.addRow(row);
        table.setModel(model);
    }

    public Object[] getFormData() {
        return new Object[]{
                txtMaThuong.getText().trim(),
                txtSoTien.getText().trim(),
                txtLyDo.getText().trim()
        };
    }

    public void enableForm(boolean b) {
        txtMaThuong.setEnabled(b);
        txtSoTien.setEnabled(b);
        txtLyDo.setEnabled(b);
    }

    public JTable getTable() {
        return table;
    }
}
