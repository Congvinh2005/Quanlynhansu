/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bt2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author AS
 */

public class Main {
    public static ArrayList<NhanVien> danhSachNhanVien = new ArrayList<>();
    public static void main(String[] args) {
        try {
            
            String inputFile = "nhanvien.dat";
            FileReader fr = new FileReader(inputFile);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                String[] arr = line.split("\\$");
                if (arr.length == 8) {
                    String hoten = arr[0];
                    String ngaysinh = arr[1];
                    String diachi = arr[2];
                    String gioitinh = arr[3];
                    String phongban = arr[4];
                    float hesoluong = Float.parseFloat(arr[5]);
                    int thamnien = Integer.parseInt(arr[6]);
                    float luongcb = Float.parseFloat(arr[7]);
                    NhanVien nv = new NhanVien(phongban, hesoluong, thamnien, luongcb,hoten, ngaysinh, diachi, gioitinh);
                    danhSachNhanVien.add(nv);
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Loi khi doc du lieu " + e.getMessage());
        }
        System.out.println("Danh sach nhan vien: ");
        for (NhanVien nv : danhSachNhanVien) {
            System.out.println(nv.toString());
        }
    }
}

