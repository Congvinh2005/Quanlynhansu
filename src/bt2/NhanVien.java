/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bt2;

/**
 *
 * @author AS
 */
public class NhanVien extends Person {
    private String phongBan;
    private float heSoLuong;
    private int thamNien;
    private float luongCoBan;

    public NhanVien() {
    }

    public NhanVien(String phongBan, float heSoLuong, int thamNien, float luongCoBan) {
        this.phongBan = phongBan;
        this.heSoLuong = heSoLuong;
        this.thamNien = thamNien;
        this.luongCoBan = luongCoBan;
    }

    public NhanVien(String phongBan, float heSoLuong, int thamNien, float luongCoBan, String hoTen, String ngaySinh, String diaChi, String gioiTinh) {
        super(hoTen, ngaySinh, diaChi, gioiTinh);
        this.phongBan = phongBan;
        this.heSoLuong = heSoLuong;
        this.thamNien = thamNien;
        this.luongCoBan = luongCoBan;
    }

    public String getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(String phongBan) {
        this.phongBan = phongBan;
    }

    public float getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(float heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public int getThamNien() {
        return thamNien;
    }

    public void setThamNien(int thamNien) {
        this.thamNien = thamNien;
    }

    public float getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(float luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    private float tinhLuongThucLinh() {
        return luongCoBan * heSoLuong * (1 + (float)thamNien/100);
    }

    @Override
    public String toString() {
        return super.toString() + phongBan + "|" + heSoLuong + "|" + thamNien + "|" + luongCoBan + "|" + tinhLuongThucLinh(); 
    }
    
    public void hienThiThongTin() {
        super.hienThiThongTin();
        System.out.println(" | Phong ban: " + phongBan + " | He so luong: " + heSoLuong + " | Tham nien: " + thamNien + " | Luong co ban: " + luongCoBan + " | Luong thuc linh: " + tinhLuongThucLinh());
    }
   
        
}
