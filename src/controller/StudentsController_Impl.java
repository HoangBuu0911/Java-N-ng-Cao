package controller;

import View.NewStudents;
import model.Students;
import model.StudentsModel;

import javax.swing.*;
import java.awt.*;

public class StudentsController_Impl implements StudentsController {
    private Component parent;
    private StudentsModel studentsModel;
    private NewStudents view;

    public StudentsController_Impl(Component parent, StudentsModel model, NewStudents view) {
        this.parent = parent;
        this.studentsModel = model;
        this.view = view;
    }

    @Override
    public void deleteStudents(int id) {
        int option = JOptionPane.showConfirmDialog(parent, "do you want to delete !", "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            studentsModel.deleteStudents(id);
        }
    }

    @Override
    public void updateStudents(int id) {
        int option = JOptionPane.showConfirmDialog(parent, view.getRootPanel(), "Repair", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            if(view.getFullName() == null){
                JOptionPane.showConfirmDialog(null, "No name entered", "error !!!", JOptionPane.OK_OPTION);
            }else {
                String fullName = view.getFullName();
                String birthYear = view.getGender();
                Students staff = new Students();
                staff.setFullName(fullName);
                staff.setGender(birthYear);
                studentsModel.updateStudents(staff, id);
            }
        }
    }
}
