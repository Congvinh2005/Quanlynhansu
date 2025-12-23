/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVCNhanVien;


    import javax.swing.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

    public class NhanVienController {

        private NhanVienView view;
        private NhanVienModel model;

        public NhanVienController() {
            view = new NhanVienView();
            model = new NhanVienModel();
            initEvents();
            view.setVisible(true);
        }

        private void initEvents() {

            // ===== BUTTON THÊM =====
            view.btnThem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    NhanVien nv = getNhanVienFromView();
                    model.themNV(nv);
                    addRowToTable(nv);
                    clearForm();
                }
            });

            // ===== BUTTON SỬA =====
            view.btnSua.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = view.table.getSelectedRow();
                    if (row == -1) {
                        JOptionPane.showMessageDialog(view, "Chọn dòng để sửa!");
                        return;
                    }

                    NhanVien nv = getNhanVienFromView();
                    model.suaNV(row, nv);
                    updateRowInTable(row, nv);
                    JOptionPane.showMessageDialog(view, "Sửa thành công!");
                }
            });

            // ===== BUTTON XÓA =====
            view.btnXoa.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = view.table.getSelectedRow();
                    if (row == -1) {
                        JOptionPane.showMessageDialog(view, "Chọn dòng để xóa!");
                        return;
                    }

                    int confirm = JOptionPane.showConfirmDialog(view,
                            "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        model.xoaNV(row);
                        view.model.removeRow(row);
                        clearForm();
                    }
                }
            });

            // ===== CLICK TRÊN TABLE LOAD LẠI FORM =====
            view.table.getSelectionModel().addListSelectionListener(e -> {
                int row = view.table.getSelectedRow();
                if (row >= 0) fillFormFromTable(row);
            });

        }


        // ================= HÀM HỖ TRỢ =================
private NhanVien getNhanVienFromView() {
            String name = view.txtUsername.getText();
            String ngaySinh = view.txtNgaySinh.getText();
            String diaChi = view.txtDiaChi.getText();
            String gioiTinh = view.rdNam.isSelected() ? "Nam" : "Nữ";
            String phongBan = view.cbPhongBan.getSelectedItem().toString();
            double hsl = Double.parseDouble(view.txtHeSoLuong.getText());
            double thamNien = Double.parseDouble(view.txtThamNien.getText());
            double luongCB = Double.parseDouble(view.txtLuongCoBan.getText());

            return new NhanVien(name, ngaySinh, diaChi, gioiTinh, phongBan, hsl, thamNien, luongCB);
        }

        private void addRowToTable(NhanVien nv) {
            view.model.addRow(new Object[]{
                    nv.getHoTen(), nv.getNgaySinh(), nv.getDiaChi(), nv.getGioiTinh(),
                    nv.getPhongBan(), nv.getHeSoLuong(), nv.getThamNien(), nv.getLuongCoBan()
            });
        }

        private void updateRowInTable(int row, NhanVien nv) {
            view.model.setValueAt(nv.getHoTen(), row, 0);
            view.model.setValueAt(nv.getNgaySinh(), row, 1);
            view.model.setValueAt(nv.getDiaChi(), row, 2);
            view.model.setValueAt(nv.getGioiTinh(), row, 3);
            view.model.setValueAt(nv.getPhongBan(), row, 4);
            view.model.setValueAt(nv.getHeSoLuong(), row, 5);
            view.model.setValueAt(nv.getThamNien(), row, 6);
            view.model.setValueAt(nv.getLuongCoBan(), row, 7);
        }

        private void fillFormFromTable(int row) {
            view.txtUsername.setText(view.model.getValueAt(row, 0).toString());
            view.txtNgaySinh.setText(view.model.getValueAt(row, 1).toString());
            view.txtDiaChi.setText(view.model.getValueAt(row, 2).toString());

            String gt = view.model.getValueAt(row, 3).toString();
            view.rdNam.setSelected(gt.equals("Nam"));
            view.rdNu.setSelected(gt.equals("Nữ"));

            view.cbPhongBan.setSelectedItem(view.model.getValueAt(row, 4).toString());
            view.txtHeSoLuong.setText(view.model.getValueAt(row, 5).toString());
            view.txtThamNien.setText(view.model.getValueAt(row, 6).toString());
            view.txtLuongCoBan.setText(view.model.getValueAt(row, 7).toString());
        }

        private void clearForm() {
            view.txtUsername.setText("");
            view.txtNgaySinh.setText("");
            view.txtDiaChi.setText("");
            view.txtHeSoLuong.setText("");
            view.txtThamNien.setText("");
            view.txtLuongCoBan.setText("");
            view.rdNam.setSelected(true);
            view.cbPhongBan.setSelectedIndex(0);
        }
    }
