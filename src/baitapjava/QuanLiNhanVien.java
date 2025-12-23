package baitapjava;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class QuanLiNhanVien extends JFrame {

    // Khai báo các biến giao diện
    private JTextField txtHoTen, txtNgaySinh, txtDiaChi, txtHeSoLuong, txtThamNien, txtLuongCoBan;
    private JRadioButton rdNam, rdNu;
    private ButtonGroup grpGender;
    private JComboBox<String> cbPhongBan;
    private JButton btnThem, btnSua, btnXoa, btnLuu, btnLamMoi;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane sp;

    // Thông tin kết nối CSDL (Sửa lại nếu cần)
    private final String URL = "jdbc:mysql://localhost:3307/qlnv";
    private final String USER = "root";
    private final String PASS = "";

    public QuanLiNhanVien() {
        setTitle("Quản lý nhân viên - Kết nối MySQL");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        initUI(); // Gọi hàm khởi tạo giao diện
        loadDataFromDB(); // Tải dữ liệu từ CSDL khi mở app
        
        setVisible(true);
    }

    // Hàm tạo giao diện (Tách ra cho gọn code)
    private void initUI() {
        JLabel lblHoTen = new JLabel("Họ tên");
        lblHoTen.setBounds(50, 30, 100, 25);
        add(lblHoTen);

        JLabel lblNgaySinh = new JLabel("Ngày sinh");
        lblNgaySinh.setBounds(50, 70, 100, 25);
        add(lblNgaySinh);

        JLabel lblDiaChi = new JLabel("Địa chỉ");
        lblDiaChi.setBounds(50, 110, 100, 25);
        add(lblDiaChi);

        JLabel lblGioiTinh = new JLabel("Giới tính");
        lblGioiTinh.setBounds(50, 150, 100, 25);
        add(lblGioiTinh);

        JLabel lblPhongBan = new JLabel("Phòng ban");
        lblPhongBan.setBounds(50, 190, 100, 25);
        add(lblPhongBan);

        JLabel lblHeSoLuong = new JLabel("Hệ số lương");
        lblHeSoLuong.setBounds(450, 30, 100, 25);
        add(lblHeSoLuong);

        JLabel lblThamNien = new JLabel("Thâm niên");
        lblThamNien.setBounds(450, 70, 100, 25);
        add(lblThamNien);

        JLabel lblLuongCoBan = new JLabel("Lương cơ bản");
        lblLuongCoBan.setBounds(450, 110, 100, 25);
        add(lblLuongCoBan);

        txtHoTen = new JTextField();
        txtHoTen.setBounds(150, 30, 250, 25);
        add(txtHoTen);

        txtNgaySinh = new JTextField();
        txtNgaySinh.setBounds(150, 70, 250, 25);
        add(txtNgaySinh);

        txtDiaChi = new JTextField();
        txtDiaChi.setBounds(150, 110, 250, 25);
        add(txtDiaChi);

        rdNam = new JRadioButton("Nam");
        rdNam.setBounds(150, 150, 70, 25);
        rdNu = new JRadioButton("Nữ");
        rdNu.setBounds(230, 150, 70, 25);
grpGender = new ButtonGroup();
        grpGender.add(rdNam);
        grpGender.add(rdNu);
        add(rdNam);
        add(rdNu);

        String[] pb = {"R&D", "Kế toán", "Nhân sự", "Kỹ thuật"};
        cbPhongBan = new JComboBox<>(pb);
        cbPhongBan.setBounds(150, 190, 250, 25);
        add(cbPhongBan);

        txtHeSoLuong = new JTextField();
        txtHeSoLuong.setBounds(550, 30, 250, 25);
        add(txtHeSoLuong);

        txtThamNien = new JTextField();
        txtThamNien.setBounds(550, 70, 250, 25);
        add(txtThamNien);

        txtLuongCoBan = new JTextField();
        txtLuongCoBan.setBounds(550, 110, 250, 25);
        add(txtLuongCoBan);

        // Thêm cột ID vào đầu tiên để quản lý update/delete
        String[] columnNames = {"ID", "Họ tên", "Ngày sinh", "Địa chỉ", "Giới tính", "Phòng ban", "HS Lương", "Thâm niên", "Lương CB"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        sp = new JScrollPane(table);
        sp.setBounds(50, 300, 800, 200);
        add(sp);

        // Nút chức năng
        btnThem = new JButton("Làm mới"); // Đổi tên nút Thêm thành Làm mới cho hợp lý
        btnThem.setBounds(120, 240, 120, 30);
        add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBounds(300, 240, 120, 30);
        add(btnSua);

        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(480, 240, 120, 30);
        add(btnXoa);

        btnLuu = new JButton("Lưu (Thêm)");
        btnLuu.setBounds(660, 240, 120, 30);
        add(btnLuu);

        // --- SỰ KIỆN CÁC NÚT ---

        // Nút Làm mới: Xóa trắng form để nhập liệu mới
        btnThem.addActionListener(e -> clearForm());

        // Nút Lưu: Thêm mới vào Database
        btnLuu.addActionListener(e -> insertNhanVien());

        // Nút Sửa: Cập nhật vào Database
        btnSua.addActionListener(e -> updateNhanVien());

        // Nút Xóa: Xóa khỏi Database
        btnXoa.addActionListener(e -> deleteNhanVien());

        // Sự kiện click vào bảng -> Đổ dữ liệu lên form
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    txtHoTen.setText(model.getValueAt(selectedRow, 1).toString());
                    txtNgaySinh.setText(model.getValueAt(selectedRow, 2).toString());
                    txtDiaChi.setText(model.getValueAt(selectedRow, 3).toString());

                    String gioiTinh = model.getValueAt(selectedRow, 4).toString();
                    if (gioiTinh.equals("Nam")) rdNam.setSelected(true);
                    else rdNu.setSelected(true);

                    cbPhongBan.setSelectedItem(model.getValueAt(selectedRow, 5).toString());
txtHeSoLuong.setText(model.getValueAt(selectedRow, 6).toString());
                    txtThamNien.setText(model.getValueAt(selectedRow, 7).toString());
                    txtLuongCoBan.setText(model.getValueAt(selectedRow, 8).toString());
                }
            }
        });
    }

    // --- CÁC HÀM XỬ LÝ DATABASE ---

    // Hàm kết nối dùng chung
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    private void loadDataFromDB() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM nhanvien";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            model.setRowCount(0); // Xóa dữ liệu cũ trên bảng
            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getInt("id")); // Lấy ID
                row.add(rs.getString("hoten"));
                row.add(rs.getString("ngaysinh"));
                row.add(rs.getString("diachi"));
                row.add(rs.getString("gioitinh"));
                row.add(rs.getString("phongban"));
                row.add(rs.getFloat("hesoluong"));
                row.add(rs.getFloat("thamnien"));
                row.add(rs.getFloat("luongcoban"));
                model.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu: " + ex.getMessage());
        }
    }

    private void insertNhanVien() {
        if (!validateForm()) return;

        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO nhanvien(hoten, ngaysinh, diachi, gioitinh, phongban, hesoluong, thamnien, luongcoban) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            setStatementParams(stmt); // Gán giá trị vào dấu ?

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Thêm mới thành công!");
            loadDataFromDB();
            clearForm();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi thêm mới: " + ex.getMessage());
        }
    }

    private void updateNhanVien() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để sửa!");
            return;
        }

        if (!validateForm()) return;

        try (Connection conn = getConnection()) {
            // Lấy ID từ cột đầu tiên (index 0) của hàng đang chọn
            int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
String sql = "UPDATE nhanvien SET hoten=?, ngaysinh=?, diachi=?, gioitinh=?, phongban=?, hesoluong=?, thamnien=?, luongcoban=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            setStatementParams(stmt); // Gán các thông tin update
            stmt.setInt(9, id); // Gán ID vào dấu ? cuối cùng

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            loadDataFromDB();
            clearForm();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi cập nhật: " + ex.getMessage());
        }
    }

    private void deleteNhanVien() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để xóa!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = getConnection()) {
                // Lấy ID để xóa
                int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());

                String sql = "DELETE FROM nhanvien WHERE id=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                loadDataFromDB();
                clearForm();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi xóa: " + ex.getMessage());
            }
        }
    }

    // Hàm phụ trợ: Gán giá trị từ form vào PreparedStatement
    private void setStatementParams(PreparedStatement stmt) throws SQLException {
        stmt.setString(1, txtHoTen.getText());
        stmt.setString(2, txtNgaySinh.getText());
        stmt.setString(3, txtDiaChi.getText());
        stmt.setString(4, rdNam.isSelected() ? "Nam" : "Nữ");
        stmt.setString(5, cbPhongBan.getSelectedItem().toString());
        stmt.setFloat(6, Float.parseFloat(txtHeSoLuong.getText()));
        stmt.setFloat(7, Float.parseFloat(txtThamNien.getText()));
        stmt.setFloat(8, Float.parseFloat(txtLuongCoBan.getText()));
    }

    // Hàm kiểm tra nhập liệu
    private boolean validateForm() {
        if (txtHoTen.getText().isEmpty() || txtHeSoLuong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên và hệ số lương!");
            return false;
        }
        if (!rdNam.isSelected() && !rdNu.isSelected()) {
             JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính!");
             return false;
        }
        return true;
    }
private void clearForm() {
        txtHoTen.setText("");
        txtNgaySinh.setText("");
        txtDiaChi.setText("");
        txtHeSoLuong.setText("");
        txtThamNien.setText("");
        txtLuongCoBan.setText("");
        grpGender.clearSelection();
        cbPhongBan.setSelectedIndex(0);
        table.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuanLiNhanVien());
    }
}
