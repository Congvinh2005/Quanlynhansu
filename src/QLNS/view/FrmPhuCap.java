package QLNS.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmPhuCap extends JPanel {

    public JButton btnThem, btnLuu, btnXoa, btnTim;
    private JTable table;
    private JTextField txtMaPC, txtTenPC, txtTienPC, txtNgayHL, txtTim;

    public FrmPhuCap() {
        initUI();
        enableForm(false);
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        /* ===== INPUT ===== */
        JPanel pnlInput = new JPanel(new GridLayout(4, 2, 4, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin phụ cấp"));

        txtMaPC = new JTextField();
        txtTenPC = new JTextField();
        txtTienPC = new JTextField();
        txtNgayHL = new JTextField();

        pnlInput.add(new JLabel("Mã phụ cấp:"));
        pnlInput.add(txtMaPC);

        pnlInput.add(new JLabel("Tên phụ cấp:"));
        pnlInput.add(txtTenPC);

        pnlInput.add(new JLabel("Tiền phụ cấp:"));
        pnlInput.add(txtTienPC);

        pnlInput.add(new JLabel("Ngày hiệu lực:"));
        pnlInput.add(txtNgayHL);

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
        scroll.setBorder(BorderFactory.createTitledBorder("Danh sách phụ cấp"));
        pnlCenter.add(scroll, BorderLayout.CENTER);

        add(pnlCenter, BorderLayout.CENTER);
    }

    public void showData(List<Object[]> list) {
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Mã PC", "Tên phụ cấp", "Tiền", "Ngày hiệu lực"}, 0
        );
        for (Object[] row : list) model.addRow(row);
        table.setModel(model);
    }

    public Object[] getFormData() {
        return new Object[]{
                txtMaPC.getText().trim(),
                txtTenPC.getText().trim(),
                txtTienPC.getText().trim(),
                txtNgayHL.getText().trim()
        };
    }

    public void enableForm(boolean b) {
        txtMaPC.setEnabled(b);
        txtTenPC.setEnabled(b);
        txtTienPC.setEnabled(b);
        txtNgayHL.setEnabled(b);
    }

    public JTable getTable() {
        return table;
    }
}
