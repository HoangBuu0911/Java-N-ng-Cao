package model;

import java.util.List;

public interface StudentsDao {
    //Create
    void insertStudents(Students students);

    //Get by ID
    Students getStudentById(int id);

    //Get All
    List<Students> getAllStudents();

    void update(Students students, int id);

    void delete(int id);


}
