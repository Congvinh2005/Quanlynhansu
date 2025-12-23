/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLNS.controller;

import QLNS.dao.TaiKhoanDAO;
import QLNS.model.TaiKhoan;
import QLNS.view.FrmQLTK;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author AS
 */
public class TaiKhoanController {

    private FrmQLTK view;
    private TaiKhoanDAO dao = new TaiKhoanDAO();

    public TaiKhoanController(FrmQLTK view) {
        this.view = view;
        new LoaiTaiKhoanController(view);
        new NhanVienController(view);
        loadTable();
        init();
    }

    private void loadTable() {
        List<TaiKhoan> list = dao.getAll();
        view.showData(list);
    }

    private void init() {
        view.btnThem.addActionListener(e -> view.enableForm(true));

        view.btnLuu.addActionListener(e -> {
            TaiKhoan tk = view.getFormData();
            if (dao.insert(tk)) {
                JOptionPane.showMessageDialog(view, "Thêm thành công");
                loadTable();
                view.enableForm(false);
            }
        });

        view.btnXoa.addActionListener(e -> {
            String tenTK = view.getSelectedTenTK();
            if (dao.delete(tenTK)) {
                loadTable();
            }
        });
    }
}