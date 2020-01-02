package View;

import controller.NewStudentsController;
import controller.NewStudentsController_Impl;
import controller.StudentsController;
import controller.StudentsController_Impl;
import model.Students;
import model.StudentsModel;
import model.TableObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class StudentsListView extends JFrame implements TableObserver {
    private JPanel panel1;
    private JPanel rootPanel;
    private JTable studentTable;

    private StudentsTableModel studentsTableModel;

    private JButton addButton;

    private StudentsModel studentsModel;

    private List<TableObserver> tableObserver = new Vector<>();

    private List<Students> staffs = new Vector<>();

    private JButton deleteButton;
    private JButton updateButton;

    String[] Gender = {"Male", "Female"};

    public StudentsListView(StudentsModel model){
        this.studentsModel = model;
        this.studentsModel.registerObserver(this);

        setTitle("Students Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(rootPanel);
        setPreferredSize(new Dimension(800,600));
        setLocation(500,200);
        pack();
        setVisible(true);

        studentsTableModel = new StudentsTableModel();
        studentTable.setModel(studentsTableModel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddStudents(e);
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDeleteStudents(e);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onUpdateStudents(e);
            }
        });

        List<Students> staff = this.studentsModel.getAllStudents();
        studentsTableModel.updateStudents(staff);

    }

    private void onAddStudents(ActionEvent e){
        NewStudentsController controller = new NewStudentsController_Impl(this, studentsModel,new NewStudents());
        controller.newStudents();
    }

    private void onDeleteStudents(ActionEvent e){
        if (studentTable.getSelectedRow() != -1) {
            StudentsController studentsController = new StudentsController_Impl(this, studentsModel, new NewStudents());
            studentsController.deleteStudents((Integer) studentsTableModel.getValueAt(studentTable.getSelectedRow(), 0));
            System.out.println(studentTable.getSelectedRow());
        }else {
            JOptionPane.showConfirmDialog(null, "Please select the box", "Delete", JOptionPane.OK_OPTION);
        }
    }

    private void onUpdateStudents(ActionEvent e){
        if (studentTable.getSelectedRow() != -1) {
            StudentsController studentsController = new StudentsController_Impl(this, studentsModel, new NewStudents());
            studentsController.updateStudents((Integer) studentsTableModel.getValueAt(studentTable.getSelectedRow(), 0));
        }else {
            JOptionPane.showConfirmDialog(null, "Please select the box", "Delete", JOptionPane.OK_OPTION);
        }
    }

    public JPanel getRootPanel(){
        return rootPanel;
    }


    @Override
    public void updateTable(List<Students> students) {
        studentsTableModel.updateStudents(students);
    }




}
