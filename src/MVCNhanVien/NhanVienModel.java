/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVCNhanVien;

/**
 *
 * @author AS
 */
import java.util.ArrayList;

public class NhanVienModel {
    private ArrayList<NhanVien> dsNhanVien;

    public NhanVienModel() {
        dsNhanVien = new ArrayList<>();
    }

    public void themNV(NhanVien nv) {
        dsNhanVien.add(nv);
    }

    public void suaNV(int index, NhanVien nv) {
        dsNhanVien.set(index, nv);
    }

    public void xoaNV(int index) {
        dsNhanVien.remove(index);
    }

    public ArrayList<NhanVien> getDS() {
        return dsNhanVien;
    }
}