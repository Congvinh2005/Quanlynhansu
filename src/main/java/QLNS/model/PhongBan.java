package QLNS.model;

public class PhongBan {
    private String maPB;
    private String tenPB;
    private String ngayThanhLap;
    private String ghiChu;

    public PhongBan() {
    }

    public PhongBan(String maPB, String tenPB, String ngayThanhLap, String ghiChu) {
        this.maPB = maPB;
        this.tenPB = tenPB;
        this.ngayThanhLap = ngayThanhLap;
        this.ghiChu = ghiChu;
    }

    public String getMaPB() { return maPB; }
    public void setMaPB(String maPB) { this.maPB = maPB; }

    public String getTenPB() { return tenPB; }
    public void setTenPB(String tenPB) { this.tenPB = tenPB; }

    public String getNgayThanhLap() { return ngayThanhLap; }
    public void setNgayThanhLap(String ngayThanhLap) { this.ngayThanhLap = ngayThanhLap; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
}