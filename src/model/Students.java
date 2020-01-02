package model;



public class Students {
    private int id;
    private String fullName;
    private String Gender;

    public Students(int id, String fullName, String gender){
        this.id = id;
        this.fullName = fullName;
        this.Gender = gender;
    }

    public Students() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String birthYear) {
        this.Gender = birthYear;
    }
}
