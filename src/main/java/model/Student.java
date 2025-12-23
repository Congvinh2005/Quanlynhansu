package model;

public class Student extends Person {

    private String Masv;
    private String Email;
    private double DTK;

    public Student(String HoTen, String NgaySinh, String DiaChi,
            String GioiTinh, String Masv, String Email, double DTK) {
        super(HoTen, NgaySinh, DiaChi, GioiTinh);
        this.Masv = Masv;
        this.Email = Email;
        this.DTK = DTK;
    }

    public String getMasv() {
        return Masv;
    }

    public String getEmail() {
        return Email;
    }

    public double getDTK() {
        return DTK;
    }
}
