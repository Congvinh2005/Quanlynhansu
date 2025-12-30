package QLNS.app;

import QLNS.controller.LoginController;
import QLNS.view.FrmLogin;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        FrmLogin view = new FrmLogin();
        new LoginController(view);
        view.setVisible(true);
    }
}