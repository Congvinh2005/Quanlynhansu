package QLNS.view;

import QLNS.model.TaiKhoan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmQLTK extends JPanel {

    // ===== BUTTON =====
    public JButton btnThem, btnSua, btnXoa, btnTim;

    // ===== TABLE =====
    public JTable table;

    // ===== FIELD =====
    public JTextField txtTenTK;
    public JTextField txtTim;
    private JPasswordField txtMatKhau;
    private JComboBox<String> cboLoaiTK, cboMaNV;

    public FrmQLTK() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblWelcome = new JLabel(
                "QUẢN LÝ TÀI KHOẢN",
                SwingConstants.CENTER
        );
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 15));
        lblWelcome.setForeground(new Color(0, 102, 204));
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 5));

        /* ================= INPUT PANEL ================= */
        JPanel pnlInput = new JPanel(new GridLayout(4, 2, 20, 10)); // Sửa lại GridLayout(4,2) để label và field thẳng hàng đẹp hơn
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin tài khoản"));

        txtTenTK = new JTextField();
        txtMatKhau = new JPasswordField();
        cboLoaiTK = new JComboBox<>();
        cboMaNV = new JComboBox<>();

        pnlInput.add(new JLabel("Tên TK:"));
        pnlInput.add(txtTenTK);

        pnlInput.add(new JLabel("Mật khẩu:"));
        pnlInput.add(txtMatKhau);

        pnlInput.add(new JLabel("Loại TK:"));
        pnlInput.add(cboLoaiTK);

        pnlInput.add(new JLabel("Mã NV:"));
        pnlInput.add(cboMaNV);

        /* ================= BUTTON PANEL ================= */
        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        pnlBtn.add(btnThem);
        pnlBtn.add(btnSua);
        pnlBtn.add(btnXoa);

        JPanel pnlTop = new JPanel(new BorderLayout(5, 5));
        pnlTop.add(pnlInput, BorderLayout.CENTER);
        pnlTop.add(pnlBtn, BorderLayout.SOUTH);

        JPanel pnlNorth = new JPanel(new BorderLayout());
        pnlNorth.add(lblWelcome, BorderLayout.NORTH);
        pnlNorth.add(pnlTop, BorderLayout.CENTER);

        add(pnlNorth, BorderLayout.NORTH);

        /* ================= CENTER ================= */
        JPanel pnlCenter = new JPanel(new BorderLayout(5, 5));

        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        pnlSearch.add(new JLabel("Tìm:"));

        txtTim = new JTextField(15);
        pnlSearch.add(txtTim);

        btnTim = new JButton("Tìm");
        pnlSearch.add(btnTim);

        pnlCenter.add(pnlSearch, BorderLayout.NORTH);

        table = new JTable();
        // Set model mặc định để bảng hiển thị tiêu đề ngay
        table.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Tên TK", "Mật khẩu", "Loại TK", "Mã NV"}
        ));
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Danh sách tài khoản"));

        pnlCenter.add(scroll, BorderLayout.CENTER);
        add(pnlCenter, BorderLayout.CENTER);
    }

    /* ================= DATA HELPER METHODS ================= */
    // View chỉ cung cấp hàm để Controller gọi, không tự xử lý logic
    
    public void showData(List<TaiKhoan> list) {
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Tên TK", "Mật khẩu", "Loại TK", "Mã NV"}, 0
        );

        for (TaiKhoan tk : list) {
            model.addRow(new Object[]{
                tk.getTenTaiKhoan(),
                tk.getMatKhau(),
                tk.getLoaiTaiKhoan(),
                tk.getMaNhanVien()
            });
        }
        table.setModel(model);
    }

    public TaiKhoan getFormData() {
        // Kiểm tra null để tránh lỗi nếu combobox chưa có dữ liệu
        String loaiTK = (cboLoaiTK.getSelectedItem() != null) ? cboLoaiTK.getSelectedItem().toString() : "";
        String maNV = (cboMaNV.getSelectedItem() != null) ? cboMaNV.getSelectedItem().toString() : "";

        return new TaiKhoan(
                txtTenTK.getText().trim(),
                new String(txtMatKhau.getPassword()),
                loaiTK,
                maNV
        );
    }

    public void fillFormFromTable() {
        int row = table.getSelectedRow();
        if (row < 0) {
            return;
        }

        txtTenTK.setText(table.getValueAt(row, 0).toString());
        txtMatKhau.setText(table.getValueAt(row, 1).toString());
        
        // Chọn item trong combobox khớp với dữ liệu bảng
        cboLoaiTK.setSelectedItem(table.getValueAt(row, 2).toString());
        cboMaNV.setSelectedItem(table.getValueAt(row, 3).toString());
    }

    public void clearForm() {
        txtTenTK.setText("");
        txtMatKhau.setText("");
        if (cboLoaiTK.getItemCount() > 0) cboLoaiTK.setSelectedIndex(0);
        if (cboMaNV.getItemCount() > 0) cboMaNV.setSelectedIndex(0);
        table.clearSelection();
    }

    public String getKeyword() {
        return txtTim.getText().trim();
    }

    public String getSelectedTenTK() {
        int row = table.getSelectedRow();
        return row < 0 ? null : table.getValueAt(row, 0).toString();
    }

    // ===== GETTER COMPONENTS =====
    // Cần thiết để Controller gán sự kiện (ActionListener)
    
    public JTable getTable() { return table; }
    public JComboBox<String> getCboLoaiTK() { return cboLoaiTK; }
    public JComboBox<String> getCboMaNV() { return cboMaNV; }
}