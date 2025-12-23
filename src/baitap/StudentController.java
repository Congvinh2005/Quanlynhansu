package baitap.controller;

import baitap.Student;
import baitap.model.*;
import baitap.view.*;
import javax.swing.*;

public class StudentController {

    private StudentModel model;
    private StudentView view;

    public StudentController(StudentModel model, StudentView view) {
        this.model = model;
        this.view = view;

        view.btnThem.addActionListener(e -> them());
        view.btnSua.addActionListener(e -> sua());
        view.btnXoa.addActionListener(e -> xoa());

        view.table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                loadToForm();
            }
        });
    }

    private void them() {
        Student st = getFormData();
        if (st != null) {
            model.add(st);
            view.model.addRow(st.toRow());
            clearForm();
        }
    }

    private void sua() {
        int row = view.table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Chọn dòng để sửa");
            return;
        }
        Student st = getFormData();
        model.getAll().set(row, st); // cập nhật model
        for (int i = 0; i < 7; i++) {
            view.model.setValueAt(st.toRow()[i], row, i);
        }
        JOptionPane.showMessageDialog(null, "Sửa thành công!");
    }

    private void xoa() {
        int row = view.table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Chọn dòng để xóa");
            return;
        }
        model.remove(row);
        view.model.removeRow(row);
        clearForm();
    }

    private Student getFormData() {
        try {
            return new Student(
                    view.txtHoTen.getText(),
                    view.txtNgaySinh.getText(),
                    view.txtDiaChi.getText(),
                    view.cbGioiTinh.getSelectedItem().toString(),
                    view.txtMaSV.getText(),
                    view.txtEmail.getText(),
                    Double.parseDouble(view.txtDiem.getText())
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ");
            return null;
        }
    }

    private void loadToForm() {
        int row = view.table.getSelectedRow();
        view.txtHoTen.setText(view.model.getValueAt(row, 0).toString());
        view.txtNgaySinh.setText(view.model.getValueAt(row, 1).toString());
        view.txtDiaChi.setText(view.model.getValueAt(row, 2).toString());
        view.cbGioiTinh.setSelectedItem(view.model.getValueAt(row, 3).toString());
        view.txtMaSV.setText(view.model.getValueAt(row, 4).toString());
        view.txtEmail.setText(view.model.getValueAt(row, 5).toString());
        view.txtDiem.setText(view.model.getValueAt(row, 6).toString());
    }

    private void clearForm() {
        view.txtHoTen.setText("");
        view.txtNgaySinh.setText("");
        view.txtDiaChi.setText("");
        view.txtMaSV.setText("");
        view.txtEmail.setText("");
        view.txtDiem.setText("");
        view.cbGioiTinh.setSelectedIndex(0);
    }
}
