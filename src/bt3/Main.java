/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bt3;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author AS
 */
public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Layout Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 500);
        frame.setLocationRelativeTo(null);

        // Panel chính theo chiều dọc
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(new Color(220,220,220));

        // ====== HÀNG 1: one – two ======
        JPanel row1 = new JPanel(new GridLayout(1, 2, 2, 0));
        row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        row1.add(makeBox("one"));
        row1.add(makeBox("two"));

        // ====== HÀNG 2: three ======
        JPanel row2 = new JPanel(new GridLayout(1, 1));
        row2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        row2.add(makeBox("three"));

        // ====== KHOẢNG CÁCH LỚN (đúng như ảnh) ======
        main.add(row1);
        main.add(row2);
        main.add(Box.createRigidArea(new Dimension(0, 50))); // khoảng trống đặc trưng

        // ====== HÀNG 3: four – five (nhỏ hơn, giữa màn hình) ======
        JPanel row3 = new JPanel();
        row3.setLayout(new BoxLayout(row3, BoxLayout.X_AXIS));
        row3.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel four = new JPanel(new GridLayout(1, 1));
        four.setMaximumSize(new Dimension(180, 80));
        four.add(makeBox("four"));

        JPanel five = new JPanel(new GridLayout(1, 1));
        five.setMaximumSize(new Dimension(180, 80));
        five.add(makeBox("five"));

        row3.add(four);
        row3.add(Box.createRigidArea(new Dimension(5, 0))); // khoảng cách giữa 2 ô
        row3.add(five);

        main.add(row3);

        frame.add(main);
        frame.setVisible(true);
    }

    private static JLabel makeBox(String text) {
        JLabel lb = new JLabel(text, SwingConstants.CENTER);
        lb.setFont(new Font("Serif", Font.PLAIN, 38));
        lb.setOpaque(true);
        lb.setBackground(new Color(220, 220, 220));
        lb.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        return lb;
    }
}

