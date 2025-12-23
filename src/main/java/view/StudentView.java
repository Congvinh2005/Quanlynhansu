package view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentView extends JFrame {

    public JTextField txtUsername, txtDiaChi, txtNgaySinh, txtMasv, txtEmail, txtDTK;
    public JRadioButton rdNam, rdNu;
    private ButtonGroup groupSex;
    public JButton btnThem, btnSua, btnXoa, btnLuu;
    public JTable table;
    public DefaultTableModel model;

    public StudentView() {
        setTitle("Quản Lý Sinh Viên");
        setSize(720, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lbUser = new JLabel("Họ Tên ");
        lbUser.setBounds(150, 30, 200, 30);
        add(lbUser);

        JLabel lbPass = new JLabel("Ngày Sinh");
        lbPass.setBounds(150, 70, 200, 30);
        add(lbPass);

        JLabel lbAddress = new JLabel("Địa Chỉ");
        lbAddress.setBounds(150, 110, 200, 30);
        add(lbAddress);

        JLabel lbSex = new JLabel("Giới Tính");
        lbSex.setBounds(150, 150, 200, 30);
        add(lbSex);

        JLabel lbmasv = new JLabel("Mã sinh viên");
        lbmasv.setBounds(150, 190, 200, 30);
        add(lbmasv);

        JLabel lbemail = new JLabel("Email");
        lbemail.setBounds(150, 230, 200, 30);
        add(lbemail);

        JLabel lbdiemtk = new JLabel("Điểm tổng kết");
        lbdiemtk.setBounds(150, 270, 200, 30);
        add(lbdiemtk);

        txtUsername = new JTextField();
        txtUsername.setBounds(300, 30, 200, 30);
        add(txtUsername);

        txtNgaySinh = new JTextField();
        txtNgaySinh.setBounds(300, 70, 200, 30);
        add(txtNgaySinh);

        txtDiaChi = new JTextField();
        txtDiaChi.setBounds(300, 110, 200, 30);
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

        txtMasv = new JTextField();
        txtMasv.setBounds(300, 190, 200, 30);
        add(txtMasv);

        txtEmail = new JTextField();
        txtEmail.setBounds(300, 230, 200, 30);
        add(txtEmail);

        txtDTK = new JTextField();
        txtDTK.setBounds(300, 270, 200, 30);
        add(txtDTK);

        btnThem = new JButton("Thêm");
        btnThem.setBounds(100, 350, 110, 30);
        add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBounds(250, 350, 110, 30);
        add(btnSua);

        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(400, 350, 110, 30);
        add(btnXoa);

        btnLuu = new JButton("Lưu");
        btnLuu.setBounds(550, 350, 110, 30);
        add(btnLuu);

        String[] columnNames = {
            "Họ tên", "Ngày sinh", "Địa chỉ", "Giới tính", "Mã sinh viên", "Email", "Điểm Tổng Kết"
        };

        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);

        // ăn hết chiều ngang Frame
        sp.setBounds(20, 390, getWidth() - 40, 200);
        add(sp);

        // Tự giãn khi resize cửa sổ
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                sp.setBounds(20, 390, getWidth() - 40, getHeight() - 430);
            }
        });

        // --- THÊM DÒNG NÀY ĐỂ CỬA SỔ HIỆN GIỮA MÀN HÌNH ---
        setLocationRelativeTo(null);
    }

    public void addTableClickListener(MouseListener ml) {
        table.addMouseListener(ml);
    }

    public void addAddListener(ActionListener a) {
        btnThem.addActionListener(a);
    }

    public void addEditListener(ActionListener a) {
        btnSua.addActionListener(a);
    }

    public void addDeleteListener(ActionListener a) {
        btnXoa.addActionListener(a);
    }

    public void addSaveListener(ActionListener a) {
        btnLuu.addActionListener(a);
    }

    public void resetForm() {
        txtUsername.setText("");
        txtNgaySinh.setText("");
        txtDiaChi.setText("");
        txtMasv.setText("");
        txtEmail.setText("");
        txtDTK.setText("");
        rdNam.setSelected(true);
    }

}
