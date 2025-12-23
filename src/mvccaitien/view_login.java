package mvccaitien;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class view_login extends JFrame {

    private JTextField tf_username;
    private JPasswordField tf_password;
    private JButton btn_login;

    public view_login() {
        super("Login MVC - Improved");

        tf_username = new JTextField(15);
        tf_password = new JPasswordField(15);
        btn_login = new JButton("Login");

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Username:"));
        panel.add(tf_username);
        panel.add(new JLabel("Password:"));
        panel.add(tf_password);
        panel.add(btn_login);

        this.setContentPane(panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public model_login getUser() {
        return new model_login(tf_username.getText(), new String(tf_password.getPassword())
        );
    }

    public void addLoginListener(ActionListener a) {
        btn_login.addActionListener(a);
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
