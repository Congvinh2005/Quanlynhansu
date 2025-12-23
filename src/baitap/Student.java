package baitap;

public class Student extends Person {
    private String maSV;
    private String email;
    private double diemTongKet;

    public Student() {}

    public Student(String hoTen, String ngaySinh, String diaChi, String gioiTinh,
                   String maSV, String email, double diemTongKet) {

        super(hoTen, ngaySinh, diaChi, gioiTinh);
        this.maSV = maSV;
        this.email = email;
        this.diemTongKet = diemTongKet;
    }

    @Override
    public void hienThi() {
        super.hienThi();
        System.out.println("Mã SV: " + maSV);
        System.out.println("Email: " + email);
        System.out.println("Điểm tổng kết: " + diemTongKet);
    }

    public Object[] toRow() {
        return new Object[]{hoTen, ngaySinh, diaChi, gioiTinh, maSV, email, diemTongKet};
    }
}
