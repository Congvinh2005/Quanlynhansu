/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kiemtra1.model;

/**
 *
 * @author AS
 */
public class NhanVien extends Person implements TinhToan {

    private String maNV;
    private String congViec;
    private double hsl;
    private double luong;

    public NhanVien(String maNV, String hoTen, String ngaySinh, String diaChi, String gioiTinh,
            String congViec, double hsl) {
        super(hoTen, ngaySinh, diaChi, gioiTinh);
        this.maNV = maNV;
        this.congViec = congViec;
        this.hsl = hsl;
        tinh_toan();
    }

    @Override
    public void tinh_toan() {
        this.luong = hsl * 2500000;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("===== NHAN VIEN =====");
        System.out.println("Ma NV: " + maNV);
        System.out.println("Ho ten: " + hoTen);
        System.out.println("Ngay sinh: " + ngaySinh);
        System.out.println("Dia chi: " + diaChi);
        System.out.println("Gioi tinh: " + gioiTinh);
        System.out.println("Cong viec: " + congViec);
        System.out.println("HSL: " + hsl);
        System.out.println("Luong: " + luong);
    }

// Getter dùng cho lưu DB
    public String getMaNV() {
        return maNV;
    }

    public String getCongViec() {
        return congViec;
    }

    public double getHsl() {
        return hsl;
    }

    public double getLuong() {
        return luong;
    }
}

