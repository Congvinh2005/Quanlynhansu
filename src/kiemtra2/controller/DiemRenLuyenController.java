/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kiemtra2.controller;

/**
 *
 * @author AS
 */
import javax.swing.table.DefaultTableModel;
import java.util.List;
import kiemtra2.dao.DiemRenLuyenDAO;
import kiemtra2.model.DiemRenLuyen;
import kiemtra2.view.FrmDiemRenLuyen;

public class DiemRenLuyenController {

    private FrmDiemRenLuyen view;
    private DiemRenLuyenDAO dao = new DiemRenLuyenDAO();

    public DiemRenLuyenController(FrmDiemRenLuyen view) {
        this.view = view;
    }

    public void loadData() {
        String namHoc = view.getCboNamHoc();
        String hocKy = view.getCboHocKy();

        List<DiemRenLuyen> list = dao.getDanhSach(namHoc, hocKy);
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);

        for (DiemRenLuyen d : list) {
            model.addRow(new Object[]{
                    d.getStt(),
                    d.getMaSV(),
                    d.getHoTen(),
                    d.getTongDiem()
            });
        }
    }
    public void loadFullData() {
        List<DiemRenLuyen> list = dao.getFullDanhSach();
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);

        for (DiemRenLuyen d : list) {
            model.addRow(new Object[]{
                    d.getStt(),
                    d.getMaSV(),
                    d.getHoTen(),
                    d.getTongDiem()
            });
        }
    }
}
