package controller;

import dao.StudentDao;
import model.Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import view.StudentView;

public class StudentController {

    private StudentView view;
    private StudentDao dao;

    public StudentController() {
        try {
            dao = new StudentDao();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không kết nối được Database!");
        }

        view = new StudentView();

        view.addAddListener(new AddListener());
        view.addEditListener(new EditListener());
        view.addDeleteListener(new DeleteListener());
        view.addSaveListener(new SaveListener());
        view.addTableClickListener(new TableClick());

        loadTable();
        view.setVisible(true);
    }

    // ====================================
    // VALIDATE NGÀY SINH
    // ====================================
    private boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(dateStr);

            if (date.after(new Date())) {
                JOptionPane.showMessageDialog(null, "Ngày sinh không được trong tương lai!");
                return false;
            }

            int year = Integer.parseInt(dateStr.substring(6));
            if (year < 1900) {
                JOptionPane.showMessageDialog(null, "Năm sinh phải ≥ 1900!");
                return false;
            }

            return true;
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Ngày sinh phải đúng định dạng dd-MM-yyyy!");
            return false;
        }
    }

    // ====================================
    // VALIDATE EMAIL GMAIL
    // ====================================
    private boolean isValidGmail(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$");
    }

    // ====================================
    // LOAD TABLE
    // ====================================
    private void loadTable() {
        view.model.setRowCount(0);
        ArrayList<Student> list = dao.getAll();

        for (Student s : list) {
            view.model.addRow(new Object[]{
                s.getHoTen(),
                s.getNgaySinh(),
                s.getDiaChi(),
                s.getGioiTinh(),
                s.getMasv(),
                s.getEmail(),
                s.getDTK()
            });
        }
    }

    // ====================================
    // ADD STUDENT
    // ====================================
    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // ----------- LẤY DỮ LIỆU -----------
                String hoten = view.txtUsername.getText().trim();
                String ngaysinh = view.txtNgaySinh.getText().trim();
                String diachi = view.txtDiaChi.getText().trim();
                String gt = view.rdNam.isSelected() ? "Nam" : "Nữ";
                String masv = view.txtMasv.getText().trim();
                String email = view.txtEmail.getText().trim();
                String dtkStr = view.txtDTK.getText().trim();

                // ----------- KIỂM TRA TRỐNG -----------
                if (hoten.isEmpty() || ngaysinh.isEmpty() || diachi.isEmpty()
                        || masv.isEmpty() || email.isEmpty() || dtkStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không được bỏ trống dữ liệu!");
                    return;
                }

                // ----------- KIỂM TRA NGÀY SINH -----------
                if (!isValidDate(ngaysinh)) {
                    return;
                }

                // ----------- KIỂM TRA EMAIL -----------
                if (!isValidGmail(email)) {
                    JOptionPane.showMessageDialog(null, "Email phải có dạng: example@gmail.com");
                    return;
                }

                // ----------- KIỂM TRA ĐIỂM SỐ -----------
                double dtk;
                try {
                    dtk = Double.parseDouble(dtkStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Điểm tổng kết phải là số!");
                    return;
                }

                // ----------- ĐỐI TƯỢNG STUDENT -----------
                Student s = new Student(hoten, ngaysinh, diachi, gt, masv, email, dtk);

                if (dao.insert(s)) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công!");
                    loadTable();
                    view.resetForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Không thể thêm! (Trùng mã SV?)");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
            }
        }
    }

    // ====================================
    // EDIT STUDENT
    // ====================================
    class EditListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String hoten = view.txtUsername.getText().trim();
                String ngaysinh = view.txtNgaySinh.getText().trim();
                String diachi = view.txtDiaChi.getText().trim();
                String gt = view.rdNam.isSelected() ? "Nam" : "Nữ";
                String masv = view.txtMasv.getText().trim();
                String email = view.txtEmail.getText().trim();
                String dtkStr = view.txtDTK.getText().trim();

                if (hoten.isEmpty() || ngaysinh.isEmpty() || diachi.isEmpty()
                        || masv.isEmpty() || email.isEmpty() || dtkStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không được bỏ trống dữ liệu!");
                    return;
                }

                if (!isValidDate(ngaysinh)) {
                    return;
                }

                if (!isValidGmail(email)) {
                    JOptionPane.showMessageDialog(null, "Email phải có dạng: example@gmail.com");
                    return;
                }

                double dtk;
                try {
                    dtk = Double.parseDouble(dtkStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Điểm tổng kết phải là số!");
                    return;
                }

                Student s = new Student(hoten, ngaysinh, diachi, gt, masv, email, dtk);

                if (dao.update(s)) {
                    JOptionPane.showMessageDialog(null, "Sửa thành công!");
                    loadTable();
                    view.resetForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Không thể sửa!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
            }
        }
    }

    // ====================================
    // DELETE STUDENT
    // ====================================
    class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String masv = view.txtMasv.getText();

            if (masv.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Bạn phải nhập mã sinh viên!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    null, "Bạn chắc muốn xóa?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                if (dao.delete(masv)) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công!");
                    loadTable();
                    view.resetForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Không thể xóa!");
                }
            }
        }
    }

    // ====================================
    // CLICK TABLE → LOAD LÊN FORM
    // ====================================
    class TableClick implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int row = view.table.getSelectedRow();

            view.txtUsername.setText(view.model.getValueAt(row, 0).toString());
            view.txtNgaySinh.setText(view.model.getValueAt(row, 1).toString());
            view.txtDiaChi.setText(view.model.getValueAt(row, 2).toString());

            String gt = view.model.getValueAt(row, 3).toString();
            if (gt.equals("Nam")) {
                view.rdNam.setSelected(true);
            } else {
                view.rdNu.setSelected(true);
            }

            view.txtMasv.setText(view.model.getValueAt(row, 4).toString());
            view.txtEmail.setText(view.model.getValueAt(row, 5).toString());
            view.txtDTK.setText(view.model.getValueAt(row, 6).toString());
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try (FileWriter fw = new FileWriter("student_data.txt")) {

                ArrayList<Student> list = dao.getAll();

                for (Student s : list) {
                    fw.write(
                            s.getHoTen() + " | "
                            + s.getNgaySinh() + " | "
                            + s.getDiaChi() + " | "
                            + s.getGioiTinh() + " | "
                            + s.getMasv() + " | "
                            + s.getEmail() + " | "
                            + s.getDTK() + "\n"
                    );
                }

                JOptionPane.showMessageDialog(null, "Đã lưu file student_data.txt!");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi lưu file!");
            }
        }
    }
}
