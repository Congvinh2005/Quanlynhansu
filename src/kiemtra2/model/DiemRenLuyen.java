/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kiemtra2.model;

/**
 *
 * @author AS
 */
public class DiemRenLuyen {
    private int stt;
    private String maSV;
    private String hoTen;
    private int tongDiem;

    public DiemRenLuyen(int stt, String maSV, String hoTen, int tongDiem) {
        this.stt = stt;
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.tongDiem = tongDiem;
    }

    public int getStt() {
        return stt;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public int getTongDiem() {
        return tongDiem;
    }
}
