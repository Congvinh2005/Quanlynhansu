/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLCSKH;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author AS
 */

public class Main {
    public static void main(String[] args) {
        // Chạy trên luồng giao diện (EDT) để an toàn
        SwingUtilities.invokeLater(() -> {
            try {
                // Set giao diện giống hệ điều hành (Windows/Mac) cho đẹp
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Mở form login đầu tiên
            new LoginFrame().setVisible(true);
        });
    }
}
