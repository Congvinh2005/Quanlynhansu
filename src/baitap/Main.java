package baitap;

import baitap.model.StudentModel;
import baitap.view.StudentView;
import baitap.controller.StudentController;

public class Main {
    public static void main(String[] args) {
        new StudentController(new StudentModel(), new StudentView());
    }
}
