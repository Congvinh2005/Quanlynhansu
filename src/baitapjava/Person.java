/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baitapjava;

import java.io.Serializable;

/**
 *
 * @author phant
 */
public class Person implements Serializable{
     private String Hoten;
    private String Ngaysinh;
    private String Diachi;
    private String Gioitinh;
    public Person(){
    }
    public Person(String Hoten, String Ngaysinh, String Diachi, String Gioitinh) {
        this.Hoten = Hoten;
        this.Ngaysinh = Ngaysinh;
        this.Diachi = Diachi;
        this.Gioitinh = Gioitinh;
    }
    public String getHoten() {
        return Hoten;
    }
    public void setHoten(String Hoten) {
        this.Hoten = Hoten;
    }
    public String getNgaysinh() {
        return Ngaysinh;
    }
    public void setNgaysinh(String Ngaysinh) {
        this.Ngaysinh = Ngaysinh;
    }

    public String getDiachi() {
        return Diachi;
    }
    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public String getGioitinh() {
        return Gioitinh;
    }
    public void setGioitinh(String Gioitinh) {
        this.Gioitinh = Gioitinh;
    }
    public String Hienthi() {
        return "Person{" + "Hoten=" + Hoten + ", Ngaysinh=" + Ngaysinh + ", Diachi=" + Diachi + ", Gioitinh=" + Gioitinh + '}';
    }
}