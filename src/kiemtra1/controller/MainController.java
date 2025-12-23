/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kiemtra1.controller;

import javax.swing.JOptionPane;
import kiemtra1.dao.NhanVienDAO;
import kiemtra1.dao.SinhVienDAO;
import kiemtra1.model.NhanVien;
import kiemtra1.model.SinhVien;
import kiemtra1.view.MainView;

/**
 *
 * @author AS
 */
public class MainController {

    MainView view;
    SinhVienDAO svDAO = new SinhVienDAO();
    NhanVienDAO nvDAO = new NhanVienDAO();

    public MainController(MainView v) {
        this.view = v;

        view.btnSVThem.addActionListener(e -> themSV());
        view.btnSVLuu.addActionListener(e -> luuSV());
        view.btnNVThem.addActionListener(e -> themNV());
        view.btnNVLuu.addActionListener(e -> luuNV());
    }

    void themSV() {
        SinhVien sv = getSV();
        sv.hienThiThongTin();
    }

    void luuSV() {
        try {
            SinhVien sv = getSV();

            if (svDAO.exists(sv.getMaSV())) {
                JOptionPane.showMessageDialog(
                        view,
                        "Mã sinh viên đã tồn tại!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            svDAO.insert(sv);

            JOptionPane.showMessageDialog(
                    view,
                    "Lưu sinh viên thành công!",
                    "Thành công",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    view,
                    "Lỗi khi lưu dữ liệu!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    void themNV() {
        NhanVien nv = getNV();
        nv.hienThiThongTin();
    }

    void luuNV() {
        try {
            NhanVien nv = getNV();

            if (nvDAO.exists(nv.getMaNV())) {
                JOptionPane.showMessageDialog(
                        view,
                        "Mã nhân viên đã tồn tại!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            nvDAO.insert(nv);

            JOptionPane.showMessageDialog(
                    view,
                    "Lưu nhân viên thành công!",
                    "Thành công",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    view,
                    "Lỗi khi lưu dữ liệu!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    SinhVien getSV() {
        return new SinhVien(
                view.sv_ma.getText(), view.sv_ten.getText(), view.sv_ns.getText(),
                view.sv_dc.getText(), (String) view.sv_gt.getSelectedItem(),
                Double.parseDouble(view.sv_dtl.getText())
        );
    }

    NhanVien getNV() {
        return new NhanVien(
                view.nv_ma.getText(), view.nv_ten.getText(), view.nv_ns.getText(),
                view.nv_dc.getText(), (String) view.nv_gt.getSelectedItem(),
                view.nv_cv.getText(), Double.parseDouble(view.nv_hsl.getText())
        );
    }
}
