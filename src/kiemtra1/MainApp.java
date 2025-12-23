/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kiemtra1;

import kiemtra1.controller.MainController;
import kiemtra1.view.MainView;

/**
 *
 * @author AS
 */
public class MainApp {

    public static void main(String[] args) {
        MainView v = new MainView();
        new MainController(v);
        v.setVisible(true);
    }
}
