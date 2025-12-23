/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baitapjava;

/**
 *
 * @author phant
 */
public class Nhan_vien extends Person{
    private String Phongban;
     private double Hesoluong;
     private int Thamnien;
     private int Luongcoban;    
    public Nhan_vien(String ht,String gt, String dc, String ns, String pb, double hsl, int tn, int luongcb) {
        super(ht, ns, dc, gt);
        this.Phongban = pb;
        this.Hesoluong = hsl;
        this.Thamnien = tn;
        this.Luongcoban = luongcb;
    }
        public String getPhongban() {
        return Phongban;
    }

    public void setPhongban(String Phongban) {
        this.Phongban = Phongban;
    }
    public double getHesoluong() {
        return Hesoluong;
    }
    public void setHesoluong(float Hesoluong) {
        this.Hesoluong = Hesoluong;
    }
    public float getThamnien() {
        return Thamnien;
    }
    public void setThamnien(int Thamnien) {
        this.Thamnien = Thamnien;
    }
    public double getLuongcoban() {
        return Luongcoban;
    }
    public void setLuongcoban(int Luongcoban) {
        this.Luongcoban = Luongcoban;
    }
    public double Tinhluong(){
        return Luongcoban*Hesoluong*(1+Thamnien/100);
    }
    public void Hienthi2(){
        System.out.println(super.Hienthi());
        System.out.println("Luong thuc linh: "+Tinhluong());
    }
}