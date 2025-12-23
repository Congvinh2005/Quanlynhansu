package mvclogin;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class view_login {

    JFrame frame;
    JTextField tf_username;
    JPasswordField tf_password;
    JLabel lbl_username, lbl_password;
    JButton btn_login;
    cotronler_login LoginController;

    public view_login() {
        frame = new JFrame();
        frame.setLayout(new BorderLayout(5, 5));
        frame.setSize(500, 500);
        frame.setTitle("Dang nhap");

        tf_username = new JTextField();
        tf_password = new JPasswordField();
        lbl_username = new JLabel("Username");
        lbl_password = new JLabel("Password");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel, BorderLayout.NORTH);
        panel.add(lbl_username);
        panel.add(tf_username);

        panel.add(lbl_password);
        panel.add(tf_password);
        btn_login = new JButton("Login");
        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model_login model = new model_login();
                model.user = tf_username.getText();
                model.pass = new String(tf_password.getPassword());
                cotronler_login control = new cotronler_login();
                control.check_login(model);
            }
        });
        frame.add(btn_login, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
