package QLNS.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FrmLogin extends JFrame {

    private JTextField txtTaiKhoan;
    private JPasswordField txtMatKhau;
    private JPanel btnLoginPanel; // Panel đóng vai trò là nút
    private JLabel lblLoginText;

    private final Color hoverColor = new Color(96, 125, 139);

    public FrmLogin() {
        setTitle("Đăng nhập hệ thống QLNS");
        setSize(500, 350);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        JPanel container = new JPanel();
        container.setBackground(Color.WHITE);
        container.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        container.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("ĐĂNG NHẬP VÀO HỆ THỐNG");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        container.add(lblTitle);

        JPanel pnlInput = new JPanel();
        pnlInput.setPreferredSize(new Dimension(350, 110));
        pnlInput.setLayout(new GridLayout(2, 1, 10, 10));
        pnlInput.setBackground(Color.WHITE);

        txtTaiKhoan = new JTextField();
        makeRounded(txtTaiKhoan);
        addPlaceholder(txtTaiKhoan, "Tên đăng nhập");
        pnlInput.add(txtTaiKhoan);

        txtMatKhau = new JPasswordField();
        makeRounded(txtMatKhau);
        addPlaceholder(txtMatKhau, "Mật khẩu");
        pnlInput.add(txtMatKhau);

        container.add(pnlInput);

        btnLoginPanel = new JPanel();
        btnLoginPanel.setPreferredSize(new Dimension(330, 45));
        btnLoginPanel.setBackground(Color.BLACK);
        btnLoginPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblLoginText = new JLabel("ĐĂNG NHẬP");
        lblLoginText.setForeground(Color.WHITE);
        lblLoginText.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnLoginPanel.add(lblLoginText);

        container.add(btnLoginPanel);

        add(container, BorderLayout.CENTER);
    }

    private void makeRounded(JComponent comp) {
        comp.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
                new EmptyBorder(8, 12, 8, 12)
        ));
    }

    private void addPlaceholder(JTextField field, String text) {
        field.setForeground(Color.GRAY);

        if (field instanceof JPasswordField) {
            ((JPasswordField)field).setEchoChar((char)0);
        }
        field.setText(text);

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(text)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                    if (field instanceof JPasswordField) {
                        ((JPasswordField)field).setEchoChar('•');
                    }
                }
            }

            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(text);
                    if (field instanceof JPasswordField) {
                        ((JPasswordField)field).setEchoChar((char)0);
                    }
                }
            }
        });
    }

    public String getUsername() {
        String text = txtTaiKhoan.getText();
        if ("Tên đăng nhập".equals(text) && txtTaiKhoan.getForeground().equals(Color.GRAY)) {
            return "";
        }
        return text;
    }

    public String getPassword() {
        String text = new String(txtMatKhau.getPassword());
        if ("Mật khẩu".equals(text) && txtMatKhau.getForeground().equals(Color.GRAY)) {
            return "";
        }
        return text;
    }

    public void addLoginListener(java.awt.event.MouseAdapter adapter) {
        btnLoginPanel.addMouseListener(adapter);
    }

    public void setLoginButtonHover(boolean isHover) {
        if (isHover) {
            btnLoginPanel.setBackground(hoverColor);
            lblLoginText.setForeground(Color.BLACK);
        } else {
            btnLoginPanel.setBackground(Color.BLACK);
            lblLoginText.setForeground(Color.WHITE);
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}