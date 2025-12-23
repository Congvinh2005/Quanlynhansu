/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kiemtra2.dao;

/**
 *
 * @author AS
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import kiemtra2.model.DiemRenLuyen;

public class DiemRenLuyenDAO {

    public List<DiemRenLuyen> getDanhSach(String namHoc, String hocKy) {
        List<DiemRenLuyen> list = new ArrayList<>();
        String sql = """
            SELECT msv, hoten, tongdiem
            FROM diemrenluyen
            WHERE namhoc = ? AND hocky = ?
        """;

        try (Connection con = DBConnection.getConn();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, namHoc);
            ps.setString(2, hocKy);

            ResultSet rs = ps.executeQuery();
            int stt = 1;
            while (rs.next()) {
                list.add(new DiemRenLuyen(
                        stt++,
                        rs.getString("msv"),
                        rs.getString("hoten"),
                        rs.getInt("tongdiem")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<DiemRenLuyen> getFullDanhSach() {
        List<DiemRenLuyen> list = new ArrayList<>();
        String sql = """
            SELECT msv, hoten, tongdiem
            FROM diemrenluyen
            """;

        try (Connection con = DBConnection.getConn();
             PreparedStatement ps = con.prepareStatement(sql)) {  

            ResultSet rs = ps.executeQuery();
            int stt = 1;
            while (rs.next()) {
                list.add(new DiemRenLuyen(
                        stt++,
                        rs.getString("msv"),
                        rs.getString("hoten"),
                        rs.getInt("tongdiem")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
