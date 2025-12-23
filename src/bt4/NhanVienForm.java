package bt4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NhanVienForm extends JFrame {

    private JRadioButton rdNam, rdNu;
    private ButtonGroup groupSex;

    private JTextField txtUsername, txtDiaChi, txtHeSoLuong, txtThamNien, txtLuongCoBan;
    private JTextField txtNgaySinh;
    private JComboBox<String> cbPhongBan;

    private JButton btnThem, btnLuu, btnSua, btnXoa;

    private JTable table;
    private DefaultTableModel model;

    public NhanVienForm() {
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
        btnThem.setBounds(100, 350, 110, 30);
        add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBounds(230, 350, 110, 30);
        add(btnSua);

        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(360, 350, 110, 30);
        add(btnXoa);

        btnLuu = new JButton("Lưu");
        btnLuu.setBounds(490, 350, 110, 30);
        add(btnLuu);

        // ==== BẢNG ====
        String[] columnNames = {
            "User", "Ngày sinh", "Địa chỉ", "Giới tính",
            "Phòng ban", "HSL", "Thâm niên", "Lương CB"
        };

        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30, 390, 650, 200);
        add(sp);

        // ========= SỰ KIỆN THÊM =========
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String user = txtUsername.getText();
                String ngaySinh = txtNgaySinh.getText();
                String diaChi = txtDiaChi.getText();
                String gioiTinh = rdNam.isSelected() ? "Nam" : "Nữ";
                String phongBan = cbPhongBan.getSelectedItem().toString();
                String hsl = txtHeSoLuong.getText();
                String thamNien = txtThamNien.getText();
                String luongCB = txtLuongCoBan.getText();

                model.addRow(new Object[]{
                    user, ngaySinh, diaChi, gioiTinh,
                    phongBan, hsl, thamNien, luongCB
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

                cbPhongBan.setSelectedItem(model.getValueAt(row, 4).toString());
                txtHeSoLuong.setText(model.getValueAt(row, 5).toString());
                txtThamNien.setText(model.getValueAt(row, 6).toString());
                txtLuongCoBan.setText(model.getValueAt(row, 7).toString());
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
                model.setValueAt(cbPhongBan.getSelectedItem().toString(), row, 4);
                model.setValueAt(txtHeSoLuong.getText(), row, 5);
                model.setValueAt(txtThamNien.getText(), row, 6);
                model.setValueAt(txtLuongCoBan.getText(), row, 7);

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
        txtHeSoLuong.setText("");
        txtThamNien.setText("");
        txtLuongCoBan.setText("");
        rdNam.setSelected(true);
        cbPhongBan.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new NhanVienForm().setVisible(true);
    }
}