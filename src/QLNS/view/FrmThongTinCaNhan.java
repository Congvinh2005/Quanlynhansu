package QLNS.view;

import QLNS.model.NhanVien;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmThongTinCaNhan extends JPanel {

    // Các thành phần giao diện
    public JButton btnThem, btnSua, btnXoa, btnTim, btnLuu;
    public JTable table;
    public JTextField txtMaNV, txtHoTen, txtNgaySinh, txtDiaChi, txtSDT, txtTim;
    private JRadioButton rdoNam, rdoNu;

    public FrmThongTinCaNhan() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblWelcome = new JLabel(
                "THÔNG TIN NGƯỜI DÙNG",
                SwingConstants.CENTER
        );
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 15));
        lblWelcome.setForeground(new Color(0, 102, 204));
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 5));

        /* ===== INPUT ===== */
        JPanel pnlInput = new JPanel(new GridLayout(3, 2, 4, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin cá nhân"));

        txtMaNV = new JTextField();
        txtHoTen = new JTextField();
        txtNgaySinh = new JTextField();
        txtDiaChi = new JTextField();
        txtSDT = new JTextField();

        rdoNam = new JRadioButton("Nam");
        rdoNu = new JRadioButton("Nữ");

        ButtonGroup bg = new ButtonGroup();
        bg.add(rdoNam);
        bg.add(rdoNu);
        rdoNam.setSelected(true);

        JPanel pnlGT = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        pnlGT.add(rdoNam);
        pnlGT.add(rdoNu);

        pnlInput.add(new JLabel("Mã NV:"));
        pnlInput.add(txtMaNV);
        pnlInput.add(new JLabel("Họ tên:"));
        pnlInput.add(txtHoTen);
        pnlInput.add(new JLabel("Ngày sinh:"));
        pnlInput.add(txtNgaySinh);
        pnlInput.add(new JLabel("Địa chỉ:"));
        pnlInput.add(txtDiaChi);
        pnlInput.add(new JLabel("Giới tính:"));
        pnlInput.add(pnlGT);
        pnlInput.add(new JLabel("SĐT:"));
        pnlInput.add(txtSDT);

        /* ===== BUTTON ===== */
        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLuu = new JButton("Lưu");
        pnlBtn.add(btnThem); pnlBtn.add(btnSua); pnlBtn.add(btnXoa); pnlBtn.add(btnLuu);

        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.add(pnlInput, BorderLayout.CENTER);
        pnlTop.add(pnlBtn, BorderLayout.SOUTH);

        JPanel pnlNorth = new JPanel(new BorderLayout());
        pnlNorth.add(lblWelcome, BorderLayout.NORTH);
        pnlNorth.add(pnlTop, BorderLayout.CENTER);

        add(pnlNorth, BorderLayout.NORTH);

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
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Mã NV", "Họ tên", "Ngày sinh", "Giới tính", "SĐT", "Địa chỉ"}
        ));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));
        pnlCenter.add(scroll, BorderLayout.CENTER);

        add(pnlCenter, BorderLayout.CENTER);
    }

    public void showData(List<NhanVien> list) {
        String[] columns = {"Mã NV", "Họ tên", "Ngày sinh", "Giới tính", "SĐT", "Địa chỉ"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (NhanVien nv : list) {
            model.addRow(new Object[]{
                    nv.getMaNV(),
                    nv.getHoTen(),
                    nv.getNgaySinh(),
                    nv.getGioiTinh(),
                    nv.getSdt(),
                    nv.getDiaChi()
            });
        }
        table.setModel(model);
    }

    public NhanVien getFormData() {
        String ma = txtMaNV.getText().trim();
        String ten = txtHoTen.getText().trim();
        String ngaySinh = txtNgaySinh.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String sdt = txtSDT.getText().trim();
        String gioiTinh = rdoNam.isSelected() ? "Nam" : "Nữ";

        return new NhanVien(ma, ten, ngaySinh, diaChi, gioiTinh, sdt);
    }

    // --- SỬA Ở ĐÂY: KHÓA MÃ KHI CLICK BẢNG ---
    public void fillFormFromTable() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtMaNV.setText(table.getValueAt(row, 0).toString());
            txtHoTen.setText(table.getValueAt(row, 1).toString());
            txtNgaySinh.setText(table.getValueAt(row, 2).toString());

            String gioitinh = table.getValueAt(row, 3).toString();
            if (gioitinh.equalsIgnoreCase("Nam")) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }

            txtSDT.setText(table.getValueAt(row, 4).toString());
            txtDiaChi.setText(table.getValueAt(row, 5).toString());

            // ==> KHÓA MÃ
            txtMaNV.setEnabled(false);
        }
    }

    public String getSelectedMaNV() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            return table.getValueAt(row, 0).toString();
        }
        return null;
    }

    // --- SỬA Ở ĐÂY: MỞ KHÓA MÃ KHI CLEAR FORM ---
    public void clearForm() {
        txtMaNV.setText("");
        txtHoTen.setText("");
        txtNgaySinh.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        rdoNam.setSelected(true);
        table.clearSelection();

        // ==> MỞ KHÓA MÃ
        txtMaNV.setEnabled(true);
    }
}