package controller;

import dao.LoginDao;
import model.LoginModel;
import view.LoginView;

public class LoginController {

    private LoginView view;
    private LoginDao dao;

    public LoginController() {
        view = new LoginView();
        view.addLoginListener(e -> login());
        view.setVisible(true);

        try {
            dao = new LoginDao();
        } catch (Exception ex) {
            view.showMessage("Không thể kết nối MySQL!");
        }
    }

    private void login() {
        LoginModel user = view.getUser();

        if (dao.checkLogin(user.getUser(), user.getPass())) {
            view.showMessage("Đăng nhập thành công!");
            view.dispose();

            new StudentController();
        } else {
            view.showMessage("Sai username hoặc password!");
        }
    }

}
