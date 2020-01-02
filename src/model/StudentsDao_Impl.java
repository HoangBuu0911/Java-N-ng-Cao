package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

public class StudentsDao_Impl implements StudentsDao {

    public StudentsDao_Impl(){
        Database db = new Database();
        String SQL_CREATE_STUDENTS_TABLE = "CREATE TABLE IF NOT EXISTS StaffTable (\n"
                + "    ID integer PRIMARY KEY,\n"
                + "    FullName text NOT NULL,\n"
                + "    Gender text\n"
                + ");";
        try {
            Statement statement = db.getConnection().createStatement();
            statement.execute(SQL_CREATE_STUDENTS_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();

    }
    @Override
    public void insertStudents(Students students) {
        Database db = new Database();
        final String SQL_CREATE_STUDENTS = "INSERT INTO StaffTable(FullName, Gender)" +
                "VALUES(?,?)";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQL_CREATE_STUDENTS, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, students.getFullName());
            ps.setString(2, students.getGender());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                students.setId(id);
                System.out.println("Inserted id: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }

    @Override
    public Students getStudentById(int id) {
        Students students = null;
        Database db = new Database();
        final String SQL_SELECT_STUDENTS_BY_ID = "SELECT * FROM StaffTable WHERE ID=?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQL_SELECT_STUDENTS_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int students_id = rs.getInt(1);
                String fullName = rs.getString(2);
                String gender = rs.getString(3);
                students = new Students(students_id, fullName, gender);
                System.out.println("ID: " + students_id + ", Full Name: " + fullName + ", Gender : " + gender);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return students;
    }

    @Override
    public List<Students> getAllStudents() {
        List<Students> students = new Vector<>();

        Database db = new Database();
        final String SQL_SELECT_ALL_STAFF = "SELECT * FROM StaffTable";
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_STAFF);
            while (rs.next()) {
                int id = rs.getInt(1);
                String fullName = rs.getString(2);
                String gender = rs.getString(3);
                Students student = new Students(id, fullName, gender);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void update(Students students, int id) {
        Database db = new Database();
        final String SQL_UPDATE_STUDENTS_BY_ID = "UPDATE StaffTable SET FullName = ?, Gender = ? WHERE ID = ?";
        PreparedStatement ps = null;
        try {
            ps = db.getConnection().prepareStatement(SQL_UPDATE_STUDENTS_BY_ID);
            ps.setString(1, students.getFullName());
            ps.setString(2, students.getGender());
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }

    @Override
    public void delete(int id) {
        Database db = new Database();
        final String SQL_DELETE_STAFF_BY_ID = "DELETE FROM StaffTable WHERE ID = ?";
        PreparedStatement ps = null;
        try {
            ps = db.getConnection().prepareStatement(SQL_DELETE_STAFF_BY_ID);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }
}
