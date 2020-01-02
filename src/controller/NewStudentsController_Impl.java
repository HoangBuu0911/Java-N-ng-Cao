package controller;

import View.NewStudents;
import model.Students;
import model.StudentsModel;

import javax.swing.*;
import java.awt.*;

public class NewStudentsController_Impl implements NewStudentsController {
    private Component parent;

    private StudentsModel studentsModel;
    private NewStudents view;
    public NewStudentsController_Impl(Component parent, StudentsModel studentsModel, NewStudents view){
        this.parent = parent;
        this.view = view;
        this.studentsModel = studentsModel;
    }
    @Override
    public void newStudents() {
        int option = JOptionPane.showConfirmDialog(parent, view.getRootPanel() , "Add", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(option == JOptionPane.YES_OPTION){
            if(view.getFullName() == null){
                JOptionPane.showConfirmDialog(null, "No name entered", "error !!!", JOptionPane.OK_OPTION);
            }else {
                String fullName = view.getFullName();
                String gender = view.getGender();
                Students students = new Students();
                students.setFullName(fullName);
                students.setGender(gender);
                studentsModel.addStudents(students);
            }
        }
    }
}
