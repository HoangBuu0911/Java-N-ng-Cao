package model;

import java.util.List;

public interface StudentsModel {
    List<Students> getAllStudents();

    void addStudents(Students students);

    void deleteStudents(int id);

    void updateStudents(Students students, int id);

    void registerObserver(TableObserver observer);

    void un_registerObserver(TableObserver observer);
}
