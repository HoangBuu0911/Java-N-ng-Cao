package model;

import java.util.List;
import java.util.Vector;

public class StudentsModel_lmpl implements StudentsModel {

    private List<TableObserver> tableObserver = new Vector<>();

    @Override
    public List<Students> getAllStudents() {
        StudentsDao dao = new StudentsDao_Impl();

        return dao.getAllStudents();
    }

    @Override
    public void addStudents(Students students) {
        StudentsDao dao = new StudentsDao_Impl();
        dao.insertStudents(students);
        notifyObserver();
    }

    @Override
    public void deleteStudents(int id) {
        StudentsDao dao = new StudentsDao_Impl();
        dao.delete(id);
        notifyObserver();
    }

    @Override
    public void updateStudents(Students students, int id) {
        StudentsDao dao = new StudentsDao_Impl();
        dao.update(students,id);
        notifyObserver();
    }

    @Override
    public void registerObserver(TableObserver observer) {
        if(!tableObserver.contains(observer)){
            tableObserver.add(observer);
        }
    }

    @Override
    public void un_registerObserver(TableObserver observer) {
        tableObserver.remove(observer);
    }

    private void notifyObserver(){
        List<Students> students = getAllStudents();
        for (TableObserver observer: tableObserver){
            observer.updateTable(students);
        }
    }
}
