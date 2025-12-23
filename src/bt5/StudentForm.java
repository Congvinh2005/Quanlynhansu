/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bt5;

import bt5.StudentForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AS
 */
public class StudentForm extends JFrame{
     private JRadioButton rdNam, rdNu;
    private ButtonGroup groupSex;

    private JTextField txtUsername, txtDiaChi, txtMaSinhVien, txtEmail, txtDiemTongKet;
    private JTextField txtNgaySinh;

    private JButton btnThem, btnLuu, btnSua, btnXoa;

    private JTable table;
    private DefaultTableModel model;

    public StudentForm() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Quản Lý Sinh Viên");
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

        JLabel lbMa = new JLabel("Mã sinh viên");
        lbMa.setBounds(150, 190, 100, 30);
        add(lbMa);

        JLabel lbEmail = new JLabel("Email");
        lbEmail.setBounds(150, 230, 100, 30);
        add(lbEmail);

        JLabel lDiem = new JLabel("Điểm tổng kết");
        lDiem.setBounds(150, 270, 100, 30);
        add(lDiem);

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

        txtMaSinhVien = new JTextField();
        txtMaSinhVien.setBounds(300, 190, 170, 30);
        add(txtMaSinhVien);

        txtEmail = new JTextField();
        txtEmail.setBounds(300, 230, 170, 30);
        add(txtEmail);

        txtDiemTongKet = new JTextField();
        txtDiemTongKet.setBounds(300, 270, 170, 30);
        add(txtDiemTongKet);

        // ==== NÚT ====
        btnThem = new JButton("Thêm");
        btnThem.setBounds(100, 320, 110, 30);
        add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBounds(230, 320, 110, 30);
        add(btnSua);

        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(360, 320, 110, 30);
        add(btnXoa);

        btnLuu = new JButton("Lưu");
        btnLuu.setBounds(490, 320, 110, 30);
        add(btnLuu);

        // ==== BẢNG ====
        String[] columnNames = {
            "Họ tên", "Ngày sinh", "Địa chỉ", "Giới tính",
            "Mã sinh viên", "Email", "Điểm tổng kết"};

        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30, 380, 650, 200);
        add(sp);

        // ========= SỰ KIỆN THÊM =========
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String user = txtUsername.getText();
                String ngaySinh = txtNgaySinh.getText();
                String diaChi = txtDiaChi.getText();
                String gioiTinh = rdNam.isSelected() ? "Nam" : "Nữ";
                String maSinhVien = txtMaSinhVien.getText();
                String email = txtEmail.getText();
                String diemTongKet = txtDiemTongKet.getText();

                model.addRow(new Object[]{
                    user, ngaySinh, diaChi, gioiTinh,
                    maSinhVien, email, diemTongKet
                });

                clearForm();
            }
        });

        // ========= CLICK LÊN BẢNG LOAD LẠI FORM =========
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                txtUsername.setText(model.getValueAt(row, 0).toString());
                txtNgaySinh.setText(model.getValueAt(row, 1).toString());
                txtDiaChi.setText(model.getValueAt(row, 2).toString());

                String sex = model.getValueAt(row, 3).toString();
                if (sex.equals("Nam"))
                    rdNam.setSelected(true);
                else
                    rdNu.setSelected(true);

                txtMaSinhVien.setText(model.getValueAt(row, 4).toString());
                txtEmail.setText(model.getValueAt(row, 5).toString());
                txtDiemTongKet.setText(model.getValueAt(row, 6).toString());
            }
        });

        // ========= NÚT SỬA =========
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
int row = table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để sửa!");
                    return;
                }

                model.setValueAt(txtUsername.getText(), row, 0);
                model.setValueAt(txtNgaySinh.getText(), row, 1);
                model.setValueAt(txtDiaChi.getText(), row, 2);
                model.setValueAt(rdNam.isSelected() ? "Nam" : "Nữ", row, 3);
                model.setValueAt(txtMaSinhVien.getText(), row, 4);
                model.setValueAt(txtEmail.getText(), row, 5);
                model.setValueAt(txtDiemTongKet.getText(), row, 6);

                JOptionPane.showMessageDialog(null, "Sửa thành công!");
            }
        });

        // ========= NÚT XÓA =========
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để xóa!");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(
                        null, "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    model.removeRow(row);
                    clearForm();
                }
            }
        });

        // ========= NÚT LƯU =========
        btnLuu.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Dữ liệu đã được lưu tạm thời trong bảng!");
        });

    }

    // ======= HÀM XÓA TRẮNG FORM =======
    private void clearForm() {
        txtUsername.setText("");
        txtDiaChi.setText("");
        txtNgaySinh.setText("");
        txtMaSinhVien.setText("");
        txtEmail.setText("");
        txtDiemTongKet.setText("");
        rdNam.setSelected(true);
    }

    public static void main(String[] args) {
        new StudentForm().setVisible(true);
    }
}
