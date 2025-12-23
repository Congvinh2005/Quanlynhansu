package baitap.model;

import baitap.Student;
import java.util.ArrayList;

public class StudentModel {
    private ArrayList<Student> ds = new ArrayList<>();

    public void add(Student st) {
        ds.add(st);
    }

    public void remove(int index) {
        ds.remove(index);
    }

    public Student get(int index) {
        return ds.get(index);
    }

    public ArrayList<Student> getAll() {
        return ds;
    }
}
