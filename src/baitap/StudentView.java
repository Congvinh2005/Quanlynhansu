package baitap.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentView extends JFrame {

    public JTextField txtHoTen, txtNgaySinh, txtDiaChi, txtMaSV, txtEmail, txtDiem;
    public JComboBox<String> cbGioiTinh;
    public JTable table;
    public DefaultTableModel model;
    public JButton btnThem, btnSua, btnXoa;

    public StudentView() {
        setTitle("Quản lý sinh viên - MVC");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel input = new JPanel(new GridLayout(8, 2, 5, 5));
        txtHoTen = new JTextField();  txtNgaySinh = new JTextField();
        txtDiaChi = new JTextField(); txtMaSV = new JTextField();
        txtEmail = new JTextField();  txtDiem = new JTextField();
        cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});

        input.add(new JLabel("Họ tên:")); input.add(txtHoTen);
        input.add(new JLabel("Ngày sinh:")); input.add(txtNgaySinh);
        input.add(new JLabel("Địa chỉ:")); input.add(txtDiaChi);
        input.add(new JLabel("Giới tính:")); input.add(cbGioiTinh);
        input.add(new JLabel("Mã SV:")); input.add(txtMaSV);
        input.add(new JLabel("Email:")); input.add(txtEmail);
        input.add(new JLabel("Điểm tổng kết:")); input.add(txtDiem);

        model = new DefaultTableModel(new String[]{
            "Họ tên", "Ngày sinh", "Địa chỉ", "Giới tính", "Mã SV", "Email", "Điểm"
        }, 0);
        table = new JTable(model);

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        JPanel buttons = new JPanel();
        buttons.add(btnThem); buttons.add(btnSua); buttons.add(btnXoa);

        add(input, BorderLayout.WEST);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        setVisible(true);
    }
}
