/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bt5;

/**
 *
 * @author AS
 */
public class Student extends Person {
    private String maSinhVien;
    private String email;
    private float diemTongKet;

    public Student() {
    }
  
    public Student(String maSinhVien, String email, float diemTongKet, String hoTen, String ngaySinh, String diaChi, String gioiTinh) {
        super(hoTen, ngaySinh, diaChi, gioiTinh);
        this.maSinhVien = maSinhVien;
        this.email = email;
        this.diemTongKet = diemTongKet;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getDiemTongKet() {
        return diemTongKet;
    }

    public void setDiemTongKet(float diemTongKet) {
        this.diemTongKet = diemTongKet;
    }

    @Override
    public String toString() {
        return super.toString() + maSinhVien + "|" + email + "|" + diemTongKet; 
    }
    
    public void hienThiThongTin() {
        super.hienThiThongTin();
        System.out.println(" | Ma sinh vien: " + maSinhVien + " | Email: " + email + " | Diem tong ket: " + diemTongKet);
    }
}
