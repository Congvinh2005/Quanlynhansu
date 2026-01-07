package QLNS.controller;

import QLNS.dao.TaiKhoanDAO;
import QLNS.model.TaiKhoan;
import QLNS.view.FrmLogin;
import QLNS.view.FrmMain;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginController {

    private FrmLogin view;
    private TaiKhoanDAO dao;

    public LoginController() {
        this.view = new FrmLogin();
        this.dao = new TaiKhoanDAO();
        init();
        this.view.setVisible(true);
    }

    private void init() {
        view.addLoginListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                login();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                view.setLoginButtonHover(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                view.setLoginButtonHover(false);
            }
        });
    }

    private void login() {
        String tk = view.getUsername();
        String mk = view.getPassword();

        if (tk.isEmpty()) {
            view.showMessage("Vui lòng nhập tên đăng nhập!");
            return;
        }
        if (mk.isEmpty()) {
            view.showMessage("Vui lòng nhập mật khẩu!");
            return;
        }

        try {
            TaiKhoan acc = dao.login(tk, mk);

            if (acc != null) {
                QLNS.util.Session.username = acc.getMaNhanVien();
                QLNS.util.Session.role = acc.getLoaiTaiKhoan();
                new FrmMain(acc.getMaNhanVien(), acc.getLoaiTaiKhoan()).setVisible(true);
                view.dispose();
            } else {
                view.showMessage("Tài khoản hoặc mật khẩu không đúng!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.showMessage("Lỗi kết nối cơ sở dữ liệu!");
        }
    }
}