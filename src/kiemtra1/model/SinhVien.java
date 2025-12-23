/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kiemtra1.model;

/**
 *
 * @author AS
 */
public class SinhVien extends Person implements TinhToan {

    private String maSV;
    private double diemTichLuy;
    private double diemTB;

    public SinhVien(String maSV, String hoTen, String ngaySinh, String diaChi, String gioiTinh, double diemTichLuy) {
        super(hoTen, ngaySinh, diaChi, gioiTinh);
        this.maSV = maSV;
        this.diemTichLuy = diemTichLuy;
        tinh_toan();
    }

    @Override
    public void tinh_toan() {
        this.diemTB = diemTichLuy * 10 / 4;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("===== SINH VIEN =====");
        System.out.println("Ma SV: " + maSV);
        System.out.println("Ho ten: " + hoTen);
        System.out.println("Ngay sinh: " + ngaySinh);
        System.out.println("Dia chi: " + diaChi);
        System.out.println("Gioi tinh: " + gioiTinh);
        System.out.println("Diem tich luy: " + diemTichLuy);
        System.out.println("Diem TB: " + diemTB);
    }

// Getter dùng cho lưu DB
    public String getMaSV() {
        return maSV;
    }

    public double getDiemTichLuy() {
        return diemTichLuy;
    }

    public double getDiemTB() {
        return diemTB;
    }
}