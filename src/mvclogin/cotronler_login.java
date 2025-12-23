package mvclogin;

public class cotronler_login {

    view_login view;
    model_login model;

    public void check_login(model_login tai_khoan) {
        if (tai_khoan.user.equals("aa") && tai_khoan.pass.equals("123")) {
            System.out.println("Dang nhap thanh cong");
        } else {
            System.out.println("User hoac pass sai");
        }
    }
}
