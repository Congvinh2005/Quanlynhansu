package QLNS.model;

public class Luong {
    private String maLuong;
    private double luongCoBan; 
    private String ghiChu;

    public Luong() {
    }

    public Luong(String maLuong, double luongCoBan, String ghiChu) {
        this.maLuong = maLuong;
        this.luongCoBan = luongCoBan;
        this.ghiChu = ghiChu;
    }

    // Getters và Setters
    public String getMaLuong() {
        return maLuong;
    }

    public void setMaLuong(String maLuong) {
        this.maLuong = maLuong;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}