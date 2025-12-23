package bt3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalculatorDemo extends JFrame {

    public CalculatorDemo() {
        setTitle("Calculator Demo");
        setSize(360, 480);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(230, 230, 230));

        JTextField display = new JTextField("0.0");
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setEditable(false);
        display.setBackground(new Color(245, 245, 245));
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 6, 6));
        panel.setBackground(new Color(220, 220, 220));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String buttons[] = {
            "1", "2", "3", "+",
            "4", "5", "6", "-",
            "7", "8", "9", "*",
            "0", ".", "C", "/"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 24));
            btn.setFocusPainted(false);
            btn.setBackground(Color.WHITE);
            btn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

            if (text.matches("[0-9]")) {
                btn.setForeground(new Color(0, 0, 180));
            } else if (text.equals("C")) {
                btn.setForeground(new Color(0, 160, 0));
            } else {
                btn.setForeground(new Color(200, 0, 0));
            }

            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);

        JButton equals = new JButton("=");
        equals.setFont(new Font("Arial", Font.BOLD, 32));
        equals.setForeground(new Color(220, 0, 0));
        equals.setBackground(Color.WHITE);
        equals.setFocusPainted(false);
        equals.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));

        add(equals, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new CalculatorDemo();
    }
}
