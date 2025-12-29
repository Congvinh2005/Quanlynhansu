package QLNS.model;

public class PhuCap {
    private String maPC;
    private String tenPC;
    private double tienPC; // Dùng double để lưu tiền
    private String ngayHieuLuc;

    public PhuCap() {
    }

    public PhuCap(String maPC, String tenPC, double tienPC, String ngayHieuLuc) {
        this.maPC = maPC;
        this.tenPC = tenPC;
        this.tienPC = tienPC;
        this.ngayHieuLuc = ngayHieuLuc;
    }

    // Getters và Setters
    public String getMaPC() { return maPC; }
    public void setMaPC(String maPC) { this.maPC = maPC; }

    public String getTenPC() { return tenPC; }
    public void setTenPC(String tenPC) { this.tenPC = tenPC; }

    public double getTienPC() { return tienPC; }
    public void setTienPC(double tienPC) { this.tienPC = tienPC; }

    public String getNgayHieuLuc() { return ngayHieuLuc; }
    public void setNgayHieuLuc(String ngayHieuLuc) { this.ngayHieuLuc = ngayHieuLuc; }
}