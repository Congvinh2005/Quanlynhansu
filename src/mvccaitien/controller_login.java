package mvccaitien;

import java.awt.event.*;

public class controller_login {

    private view_login view;

    public controller_login() {
        view = new view_login();
        view.addLoginListener(new LoginListener());
        view.setVisible(true);
    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            model_login user = view.getUser();

            if (checkUser(user)) {
                view.showMessage("Đăng nhập thành công!");
            } else {
                view.showMessage("Sai username hoặc password!");
            }
        }
    }

    public boolean checkUser(model_login user) {
        return user.getUser().equals("aa")
                && user.getPass().equals("123");
    }
}
