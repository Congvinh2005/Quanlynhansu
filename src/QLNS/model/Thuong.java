package QLNS.model;

public class Thuong {
    private String maThuong;
    private double soTien; // Dùng double để lưu tiền
    private String lyDo;

    public Thuong() {
    }

    public Thuong(String maThuong, double soTien, String lyDo) {
        this.maThuong = maThuong;
        this.soTien = soTien;
        this.lyDo = lyDo;
    }

    // Getters và Setters
    public String getMaThuong() { return maThuong; }
    public void setMaThuong(String maThuong) { this.maThuong = maThuong; }

    public double getSoTien() { return soTien; }
    public void setSoTien(double soTien) { this.soTien = soTien; }

    public String getLyDo() { return lyDo; }
    public void setLyDo(String lyDo) { this.lyDo = lyDo; }
}