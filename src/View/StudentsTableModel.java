package View;
import model.Students;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class StudentsTableModel extends AbstractTableModel {

    private List<Students> studentsList = new ArrayList<Students>();

    private static final String[] columnNames = {"ID", "Full Name", "Gender"};

    private static final int ID = 0;
    private static final int FULL_NAME = 1;
    private static final int GENDER = 2;

    @Override
    public int getRowCount() {
        return studentsList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Students student = studentsList.get(rowIndex);
        if(columnIndex == ID){
            return student.getId();
        }else if(columnIndex == FULL_NAME){
            return student.getFullName();
        }else if(columnIndex == GENDER){
            return student.getGender();
        }else {
            return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void updateStudents(List<Students> students){
        this.studentsList.clear();
        this.studentsList.addAll(students);
        fireTableDataChanged();
    }
}
