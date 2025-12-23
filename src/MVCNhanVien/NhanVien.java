/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVCNhanVien;

/**
 *
 * @author AS
 */
public class NhanVien {
    private String hoTen, ngaySinh, diaChi, gioiTinh, phongBan;
    private double heSoLuong, thamNien, luongCoBan;

    public NhanVien(String hoTen, String ngaySinh, String diaChi, String gioiTinh, String phongBan, double heSoLuong, double thamNien, double luongCoBan) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.phongBan = phongBan;
        this.heSoLuong = heSoLuong;
        this.thamNien = thamNien;
        this.luongCoBan = luongCoBan;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getPhongBan() {
        return phongBan;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public double getThamNien() {
        return thamNien;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setPhongBan(String phongBan) {
        this.phongBan = phongBan;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public void setThamNien(double thamNien) {
        this.thamNien = thamNien;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

}
