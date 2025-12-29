package QLNS.controller;


import QLNS.dao.TaiKhoanDAO;
import QLNS.model.TaiKhoan;
import QLNS.view.FrmLogin;
import QLNS.view.FrmMain;

import javax.swing.*;

public class LoginController {

    private FrmLogin view;
    private TaiKhoanDAO dao = new TaiKhoanDAO();

    public LoginController(FrmLogin view) {
        this.view = view;
        init();
    }

    private void init() {
        view.getBtnLogin().addActionListener(e -> login());
    }

    private void login() {
        String tk = view.getTxtTaiKhoan().getText().trim();
        String mk = new String(view.getTxtMatKhau().getPassword()).trim();

        if (tk.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Chưa nhập tài khoản");
            return;
        }
        if (mk.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Chưa nhập mật khẩu");
            return;
        }

        TaiKhoan acc = dao.login(tk, mk);
        if (acc != null) {
            new FrmMain(acc.getMaNhanVien(), acc.getLoaiTaiKhoan()).setVisible(true);
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view,
                    "Tài khoản hoặc mật khẩu không đúng",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}