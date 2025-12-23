package baitap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StudentForm extends JFrame {

    private JTextField txtHoTen, txtNgaySinh, txtDiaChi, txtMaSV, txtEmail, txtDiem;
    private JComboBox<String> cbGioiTinh;
    private JTable table;
    private DefaultTableModel model;

    public StudentForm() {
        setTitle("Quản lý sinh viên");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel nhập liệu
        JPanel input = new JPanel(new GridLayout(8, 2, 5, 5));
        txtHoTen = new JTextField();
        txtNgaySinh = new JTextField();
        txtDiaChi = new JTextField();
        txtMaSV = new JTextField();
        txtEmail = new JTextField();
        txtDiem = new JTextField();
        cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});

        input.add(new JLabel("Họ tên:"));
        input.add(txtHoTen);
        input.add(new JLabel("Ngày sinh:"));
        input.add(txtNgaySinh);
        input.add(new JLabel("Địa chỉ:"));
        input.add(txtDiaChi);
        input.add(new JLabel("Giới tính:"));
        input.add(cbGioiTinh);
        input.add(new JLabel("Mã SV:"));
        input.add(txtMaSV);
        input.add(new JLabel("Email:"));
        input.add(txtEmail);
        input.add(new JLabel("Điểm tổng kết:"));
        input.add(txtDiem);

        // Bảng dữ liệu
        model = new DefaultTableModel(new String[]{
            "Họ tên", "Ngày sinh", "Địa chỉ", "Giới tính", "Mã SV", "Email", "Điểm"
        }, 0);
        table = new JTable(model);

        // ========== SỰ KIỆN CLICK LÊN BẢNG → ĐỔ DỮ LIỆU LÊN FORM ==========
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    txtHoTen.setText(model.getValueAt(row, 0).toString());
                    txtNgaySinh.setText(model.getValueAt(row, 1).toString());
                    txtDiaChi.setText(model.getValueAt(row, 2).toString());
                    cbGioiTinh.setSelectedItem(model.getValueAt(row, 3).toString());
                    txtMaSV.setText(model.getValueAt(row, 4).toString());
                    txtEmail.setText(model.getValueAt(row, 5).toString());
                    txtDiem.setText(model.getValueAt(row, 6).toString());
                }
            }
        });

        // Nút
        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");

        JPanel buttons = new JPanel();
        buttons.add(btnThem);
        buttons.add(btnSua);
        buttons.add(btnXoa);

        add(input, BorderLayout.WEST);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        // ========== SỰ KIỆN BUTTON ==========

        // d. Thêm dữ liệu vào table
        btnThem.addActionListener(e -> {
            Student st = getStudentFromForm();
            if (st != null) {
                model.addRow(st.toRow());
            }
            clearForm();
        });

        // e. Sửa dữ liệu
        btnSua.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để sửa!");
                return;
            }

            model.setValueAt(txtHoTen.getText(), row, 0);
            model.setValueAt(txtNgaySinh.getText(), row, 1);
            model.setValueAt(txtDiaChi.getText(), row, 2);
            model.setValueAt(cbGioiTinh.getSelectedItem().toString(), row, 3);
            model.setValueAt(txtMaSV.getText(), row, 4);
            model.setValueAt(txtEmail.getText(), row, 5);
            model.setValueAt(txtDiem.getText(), row, 6);

            JOptionPane.showMessageDialog(null, "Sửa thành công!");
        });

        // f. Xóa dòng
        btnXoa.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để xóa!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null, 
                "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                model.removeRow(row);
                clearForm();
            }
        });

        setVisible(true);
    }

    // Hàm lấy dữ liệu từ form
    private Student getStudentFromForm() {
        try {
            String hoTen = txtHoTen.getText();
            String ns = txtNgaySinh.getText();
            String dc = txtDiaChi.getText();
            String gt = cbGioiTinh.getSelectedItem().toString();
            String ma = txtMaSV.getText();
            String email = txtEmail.getText();
            double diem = Double.parseDouble(txtDiem.getText());

            return new Student(hoTen, ns, dc, gt, ma, email, diem);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ!");
            return null;
        }
    }

    private void clearForm() {
        txtHoTen.setText("");
        txtNgaySinh.setText("");
        txtDiaChi.setText("");
        txtMaSV.setText("");
        txtEmail.setText("");
        txtDiem.setText("");
        cbGioiTinh.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new StudentForm();
    }
}
