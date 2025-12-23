package QLNS.controller;

import QLNS.dao.NhanVienDAO;
import QLNS.view.FrmQLTK;

import java.util.List;

public class NhanVienController {

    private FrmQLTK view;
    private NhanVienDAO dao = new NhanVienDAO();

    public NhanVienController(FrmQLTK view) {
        this.view = view;
        loadMaNV();
    }

    private void loadMaNV() {
        List<String> list = dao.getAllMaNV();
        view.getCboMaNV().removeAllItems();

        for (String maNV : list) {
            view.getCboMaNV().addItem(maNV);
        }
    }
}
