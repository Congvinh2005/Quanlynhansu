package QLNS.controller;

import QLNS.dao.LoaiTaiKhoanDAO;
import QLNS.view.FrmQLTK;

import java.util.List;

public class LoaiTaiKhoanController {

    private FrmQLTK view;
    private LoaiTaiKhoanDAO dao = new LoaiTaiKhoanDAO();

    public LoaiTaiKhoanController(FrmQLTK view) {
        this.view = view;
        loadLoaiTK();
    }

    private void loadLoaiTK() {
        List<String> list = dao.getAllLoaiTK();
        view.getCboLoaiTK().removeAllItems();

        for (String loai : list) {
            view.getCboLoaiTK().addItem(loai);
        }
    }
}