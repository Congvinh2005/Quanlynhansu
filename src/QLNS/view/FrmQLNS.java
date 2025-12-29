package QLNS.view;

import QLNS.model.ChucVu;
import QLNS.model.PhongBan;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmQLNS extends JPanel {

    public JButton btnPhanCong, btnXoa, btnTim, btnLuu; // btnLuu có thể dùng để reset
    public JTable table;
    public JTextField txtTim;

    private JTextField txtMaNV, txtHoTen;
    private JComboBox<String> cboPhongBan, cboChucVu;

    public FrmQLNS() {
        initUI();
        txtMaNV.setEnabled(false);
        txtHoTen.setEnabled(false);
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblWelcome = new JLabel("QUẢN LÝ NHÂN SỰ", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 15));
        lblWelcome.setForeground(new Color(0, 102, 204));
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 5));

        /* ===== INPUT ===== */
        JPanel pnlInput = new JPanel(new GridLayout(4, 2, 4, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Phân công nhân sự"));

        txtMaNV = new JTextField();
        txtMaNV.setFont(new Font("Segoe UI", Font.BOLD, 15));
        txtHoTen = new JTextField();
        cboPhongBan = new JComboBox<>();
        cboChucVu = new JComboBox<>();

        pnlInput.add(new JLabel("Mã NV:")); pnlInput.add(txtMaNV);
        pnlInput.add(new JLabel("Họ tên:")); pnlInput.add(txtHoTen);
        pnlInput.add(new JLabel("Phòng ban:")); pnlInput.add(cboPhongBan);
        pnlInput.add(new JLabel("Chức vụ:")); pnlInput.add(cboChucVu);

        /* ===== BUTTON ===== */
        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        btnPhanCong = new JButton("Phân công");
        btnXoa = new JButton("Xóa");
        btnLuu = new JButton("Làm mới"); // Đổi tên cho hợp ngữ cảnh

        pnlBtn.add(btnPhanCong);
        pnlBtn.add(btnXoa);
        pnlBtn.add(btnLuu);

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
        txtTim = new JTextField(15); pnlSearch.add(txtTim);
        btnTim = new JButton("Tìm"); pnlSearch.add(btnTim);
        pnlCenter.add(pnlSearch, BorderLayout.NORTH);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Mã NV", "Họ tên", "Phòng ban", "Chức vụ"}
        ));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Danh sách nhân sự"));
        pnlCenter.add(scroll, BorderLayout.CENTER);
        add(pnlCenter, BorderLayout.CENTER);
    }

    // ===== HÀM HỖ TRỢ DỮ LIỆU =====

    // 1. Load danh sách vào bảng
    public void showData(List<Object[]> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa cũ
        for (Object[] r : list) {
            model.addRow(r);
        }
    }

    // 2. Load ComboBox (Controller sẽ gọi)
    public void loadCombos(List<PhongBan> listPB, List<ChucVu> listCV) {
        cboPhongBan.removeAllItems();
        cboPhongBan.addItem("--- Chọn phòng ban ---"); // Item đầu tiên (index 0)
        for (PhongBan pb : listPB) {
            cboPhongBan.addItem(pb.getTenPB());
        }

        cboChucVu.removeAllItems();
        cboChucVu.addItem("--- Chọn chức vụ ---"); // Item đầu tiên (index 0)
        for (ChucVu cv : listCV) {
            cboChucVu.addItem(cv.getTenCV());
        }
    }

    // 3. Đổ dữ liệu từ Bảng lên Form
    public void fillForm() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtMaNV.setText(table.getValueAt(row, 0).toString());
            txtHoTen.setText(table.getValueAt(row, 1).toString());

            // Xử lý Phòng Ban
            Object pbValue = table.getValueAt(row, 2);
            if (pbValue == null || pbValue.toString().equals("Chưa có")) {
                cboPhongBan.setSelectedIndex(0); // Chọn "--- Chọn phòng ban ---"
            } else {
                cboPhongBan.setSelectedItem(pbValue.toString());
            }

            // Xử lý Chức Vụ
            Object cvValue = table.getValueAt(row, 3);
            if (cvValue == null || cvValue.toString().equals("Chưa có")) {
                cboChucVu.setSelectedIndex(0); // Chọn "--- Chọn chức vụ ---"
            } else {
                cboChucVu.setSelectedItem(cvValue.toString());
            }

            // LOGIC QUAN TRỌNG: Khóa Mã NV và Họ Tên khi đang chọn để Phân công
            txtMaNV.setEnabled(false);
            txtHoTen.setEnabled(false);
        }
    }

    // 4. Lấy dữ liệu tên đang chọn trong Combo
    public String getSelectedTenPB() {
        if (cboPhongBan.getSelectedIndex() <= 0) return null;
        return cboPhongBan.getSelectedItem().toString();
    }

    public String getSelectedTenCV() {
        if (cboChucVu.getSelectedIndex() <= 0) return null;
        return cboChucVu.getSelectedItem().toString();
    }

    public String getSelectedMaNV() {
        return txtMaNV.getText();
    }

    // 5. Reset form (Mở khóa lại input)
    public void clearForm() {
        txtMaNV.setText("");
        txtHoTen.setText("");
        cboPhongBan.setSelectedIndex(0);
        cboChucVu.setSelectedIndex(0);
        table.clearSelection();

        // Mở khóa lại để có thể nhìn rõ hoặc dùng cho chức năng khác nếu cần
    
    }
}