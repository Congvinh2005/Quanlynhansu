package QLNS.view;

import QLNS.model.Luong;
import QLNS.model.PhuCap;
import QLNS.model.Thuong;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FrmBangLuong extends JPanel {

    // Public các nút
    public JButton btnCapNhat, btnXoa, btnTim, btnLuu;
    public JTable table;
    public JTextField txtTim;

    // Private fields
    private JTextField txtMaNV, txtHoTen, txtLuongThucLinh;
    // ComboBox chứa String (số tiền)
    private JComboBox<String> cboLuongCoBan, cboTienThuong, cboTienPhuCap;

    // --- QUAN TRỌNG: Biến lưu danh sách gốc để tra cứu ngược từ Tiền ra Mã ---
    private List<Luong> listLuongGoc = new ArrayList<>();
    private List<Thuong> listThuongGoc = new ArrayList<>();
    private List<PhuCap> listPhuCapGoc = new ArrayList<>();

    public FrmBangLuong() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblWelcome = new JLabel("BẢNG LƯƠNG NHÂN SỰ", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 15));
        lblWelcome.setForeground(new Color(0, 102, 204));
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 5));

        /* ================= INPUT PANEL ================= */
        JPanel pnlInput = new JPanel(new GridLayout(3, 4, 10, 8));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin bảng lương"));

        txtMaNV = new JTextField();
        txtHoTen = new JTextField();
        txtLuongThucLinh = new JTextField();
        txtLuongThucLinh.setEditable(false);
        txtLuongThucLinh.setForeground(Color.RED);
        txtLuongThucLinh.setFont(new Font("Arial", Font.BOLD, 12));

        cboLuongCoBan = new JComboBox<>();
        cboTienThuong = new JComboBox<>();
        cboTienPhuCap = new JComboBox<>();

        pnlInput.add(new JLabel("Mã nhân viên:")); pnlInput.add(txtMaNV);
        pnlInput.add(new JLabel("Lương cơ bản:")); pnlInput.add(cboLuongCoBan);
        pnlInput.add(new JLabel("Họ tên:")); pnlInput.add(txtHoTen);
        pnlInput.add(new JLabel("Tiền phụ cấp:")); pnlInput.add(cboTienPhuCap);
        pnlInput.add(new JLabel("Tiền thưởng:")); pnlInput.add(cboTienThuong);
        pnlInput.add(new JLabel("Lương thực lĩnh:")); pnlInput.add(txtLuongThucLinh);

        /* ================= BUTTON PANEL ================= */
        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        btnCapNhat = new JButton("Cập nhật");
        btnXoa = new JButton("Xóa");
        btnLuu = new JButton("Làm mới");

        pnlBtn.add(btnCapNhat); pnlBtn.add(btnXoa); pnlBtn.add(btnLuu);

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
        
        pnlSearch.add(new JLabel("Tìm kiếm:"));
        txtTim = new JTextField(15); pnlSearch.add(txtTim);
        btnTim = new JButton("Tìm"); pnlSearch.add(btnTim);
        pnlCenter.add(pnlSearch, BorderLayout.NORTH);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{}, 
                new String[]{"Mã NV", "Họ tên", "Lương CB", "Thưởng", "Phụ cấp", "Thực Lĩnh"}
        ));
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Danh sách bảng lương"));
        pnlCenter.add(scroll, BorderLayout.CENTER);
        add(pnlCenter, BorderLayout.CENTER);
    }

    // ==========================================================
    // CÁC HÀM HỖ TRỢ
    // ==========================================================

    // 1. Load danh sách vào ComboBox (CHỈ HIỆN SỐ TIỀN)
    public void loadCombos(List<Luong> listLuong, List<Thuong> listThuong, List<PhuCap> listPC) {
        // Lưu lại danh sách gốc để dùng cho việc tra cứu ID sau này
        this.listLuongGoc = listLuong;
        this.listThuongGoc = listThuong;
        this.listPhuCapGoc = listPC;

        // --- LƯƠNG CƠ BẢN ---
        cboLuongCoBan.removeAllItems();
        cboLuongCoBan.addItem("0"); // Item index 0 là mặc định
        for (Luong l : listLuong) {
            cboLuongCoBan.addItem(String.valueOf(l.getLuongCoBan())); // Chỉ hiện số
        }

        // --- TIỀN THƯỞNG ---
        cboTienThuong.removeAllItems();
        cboTienThuong.addItem("0");
        for (Thuong t : listThuong) {
            cboTienThuong.addItem(String.valueOf(t.getSoTien())); // Chỉ hiện số
        }

        // --- TIỀN PHỤ CẤP ---
        cboTienPhuCap.removeAllItems();
        cboTienPhuCap.addItem("0");
        for (PhuCap p : listPC) {
            cboTienPhuCap.addItem(String.valueOf(p.getTienPC())); // Chỉ hiện số
        }
    }

    // 2. Hiển thị dữ liệu lên bảng (Có check Null)
    public void showData(List<Object[]> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Object[] row : list) {
            // row[0]: MaNV, row[1]: TenNV, row[2]: LuongCB, row[3]: Thuong, row[4]: PhuCap
            
            // Check null an toàn
            double lcb = (row[2] != null) ? Double.parseDouble(row[2].toString()) : 0;
            double thuong = (row[3] != null) ? Double.parseDouble(row[3].toString()) : 0;
            double phucap = (row[4] != null) ? Double.parseDouble(row[4].toString()) : 0;
            double thucLinh = lcb + thuong + phucap;

            model.addRow(new Object[]{
                row[0], 
                row[1], 
                lcb,      // Hiển thị số gốc
                thuong, 
                phucap, 
                thucLinh  
            });
        }
        table.setModel(model);
    }

    // 3. Đổ dữ liệu từ Bảng lên Form
    public void fillForm() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtMaNV.setText(table.getValueAt(row, 0).toString());
            txtHoTen.setText(table.getValueAt(row, 1).toString());
            
            // Chọn giá trị trên ComboBox khớp với bảng
            // Lưu ý: Cách này chọn theo chuỗi hiển thị
            setSelectedComboValue(cboLuongCoBan, table.getValueAt(row, 2).toString());
            setSelectedComboValue(cboTienThuong, table.getValueAt(row, 3).toString());
            setSelectedComboValue(cboTienPhuCap, table.getValueAt(row, 4).toString());

            txtLuongThucLinh.setText(table.getValueAt(row, 5).toString());

            // KHÓA Mã và Tên
            txtMaNV.setEnabled(false);
            txtHoTen.setEnabled(false);
        }
    }

    private void setSelectedComboValue(JComboBox<String> combo, String value) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).equals(value)) {
                combo.setSelectedIndex(i);
                return;
            }
        }
        combo.setSelectedIndex(0); // Nếu không tìm thấy thì về 0
    }

    // 4. Tính toán hiển thị (Đơn giản hóa vì ComboBox giờ chỉ chứa số)
    public void updateThucLinhDisplay() {
        double lcb = parseMoney(cboLuongCoBan);
        double thuong = parseMoney(cboTienThuong);
        double pc = parseMoney(cboTienPhuCap);
        txtLuongThucLinh.setText(String.valueOf(lcb + thuong + pc));
    }

    private double parseMoney(JComboBox<String> combo) {
        try {
            if (combo.getSelectedItem() != null) {
                return Double.parseDouble(combo.getSelectedItem().toString());
            }
        } catch (Exception e) {}
        return 0;
    }

    // 5. CÁC HÀM LẤY MÃ (ID) ĐỂ LƯU XUỐNG DB
    // Logic: Lấy index đang chọn -> truy xuất vào List Gốc để lấy ID
    
    public String getSelectedMaLuong() {
        int index = cboLuongCoBan.getSelectedIndex();
        // index 0 là "0" (không có mã), nên index thực trong list là index - 1
        if (index > 0 && index - 1 < listLuongGoc.size()) {
            return listLuongGoc.get(index - 1).getMaLuong();
        }
        return null; // Chọn 0 hoặc lỗi
    }

    public String getSelectedMaThuong() {
        int index = cboTienThuong.getSelectedIndex();
        if (index > 0 && index - 1 < listThuongGoc.size()) {
            return listThuongGoc.get(index - 1).getMaThuong();
        }
        return null;
    }

    public String getSelectedMaPhuCap() {
        int index = cboTienPhuCap.getSelectedIndex();
        if (index > 0 && index - 1 < listPhuCapGoc.size()) {
            return listPhuCapGoc.get(index - 1).getMaPC();
        }
        return null;
    }
    
    public String getSelectedMaNV() {
        return txtMaNV.getText();
    }

    // 6. Reset form
    public void clearForm() {
        txtMaNV.setText("");
        txtHoTen.setText("");
        cboLuongCoBan.setSelectedIndex(0);
        cboTienThuong.setSelectedIndex(0);
        cboTienPhuCap.setSelectedIndex(0);
        txtLuongThucLinh.setText("");
        table.clearSelection();
        
        // MỞ KHÓA
        txtMaNV.setEnabled(true);
        txtHoTen.setEnabled(true);
    }
    
    // Getter
    public JComboBox<String> getCboLuongCoBan() { return cboLuongCoBan; }
    public JComboBox<String> getCboTienThuong() { return cboTienThuong; }
    public JComboBox<String> getCboTienPhuCap() { return cboTienPhuCap; }
}