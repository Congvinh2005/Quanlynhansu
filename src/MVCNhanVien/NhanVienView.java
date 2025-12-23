/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVCNhanVien;

/**
 *
 * @author AS
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NhanVienView extends JFrame {

    public JTextField txtUsername, txtDiaChi, txtHeSoLuong, txtThamNien, txtLuongCoBan, txtNgaySinh;
    public JRadioButton rdNam, rdNu;
    public JComboBox<String> cbPhongBan;
    public JButton btnThem, btnSua, btnXoa;
    public JTable table;
    public DefaultTableModel model;
    public ButtonGroup groupSex;

    public NhanVienView() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Quản Lý Nhân Viên");
        setSize(720, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lbUser = new JLabel("Họ Tên ");
        lbUser.setBounds(150, 30, 100, 30);
        add(lbUser);

        JLabel lbPass = new JLabel("Ngày Sinh");
        lbPass.setBounds(150, 70, 100, 30);
        add(lbPass);

        JLabel lbAddress = new JLabel("Địa Chỉ");
        lbAddress.setBounds(150, 110, 100, 30);
        add(lbAddress);

        JLabel lbSex = new JLabel("Giới Tính");
        lbSex.setBounds(150, 150, 100, 30);
        add(lbSex);

        JLabel lbDepartment = new JLabel("Phòng Ban");
        lbDepartment.setBounds(150, 190, 100, 30);
        add(lbDepartment);

        JLabel lbHSL = new JLabel("Hệ số lương");
        lbHSL.setBounds(150, 230, 100, 30);
        add(lbHSL);

        JLabel lThamNien = new JLabel("Thâm niên");
        lThamNien.setBounds(150, 270, 100, 30);
        add(lThamNien);

        JLabel lLuongCoBan = new JLabel("Lương Cơ bản");
        lLuongCoBan.setBounds(150, 310, 100, 30);
        add(lLuongCoBan);

        txtUsername = new JTextField();
        txtUsername.setBounds(300, 30, 170, 30);
        add(txtUsername);

        txtNgaySinh = new JTextField();
        txtNgaySinh.setBounds(300, 70, 170, 30);
        add(txtNgaySinh);

        txtDiaChi = new JTextField();
        txtDiaChi.setBounds(300, 110, 170, 30);
        add(txtDiaChi);

        rdNam = new JRadioButton("Nam");
        rdNam.setBounds(300, 150, 70, 30);

        rdNu = new JRadioButton("Nữ");
        rdNu.setBounds(400, 150, 70, 30);

        groupSex = new ButtonGroup();
        groupSex.add(rdNam);
        groupSex.add(rdNu);

        rdNam.setSelected(true);

        add(rdNam);
        add(rdNu);

        txtHeSoLuong = new JTextField();
        txtHeSoLuong.setBounds(300, 230, 170, 30);
        add(txtHeSoLuong);

        txtThamNien = new JTextField();
        txtThamNien.setBounds(300, 270, 170, 30);
        add(txtThamNien);

        txtLuongCoBan = new JTextField();
        txtLuongCoBan.setBounds(300, 310, 170, 30);
        add(txtLuongCoBan);

        cbPhongBan = new JComboBox<>(new String[]{"Kế Toán", "Nhân Sự", "Kỹ Thuật", "Bán Hàng"});
        cbPhongBan.setBounds(300, 190, 170, 30);
        add(cbPhongBan);
        // ==== NÚT ====
btnThem = new JButton("Thêm");
        btnThem.setBounds(110, 350, 110, 30);
        add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBounds(260, 350, 110, 30);
        add(btnSua);

        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(410, 350, 110, 30);
        add(btnXoa);

        // ==== BẢNG ====
        model = new DefaultTableModel(new Object[]{
            "User", "Ngày sinh", "Địa chỉ", "Giới tính",
            "Phòng ban", "HSL", "Thâm niên", "Lương CB"
        }, 0);

        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30, 390, 650, 200);
        add(sp);
    }
}
