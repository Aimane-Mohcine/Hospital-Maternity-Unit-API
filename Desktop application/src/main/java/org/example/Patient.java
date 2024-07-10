package org.example;

public class Patient {

    private Integer id;
    private String surname;
    private String forename;
    private String nhsNumber;

    public Integer getId() {
        return id;
    }

    public String getNhsNumber(){

        return nhsNumber;
    }
    public String getSurname() {
        return surname;
    }
    public String getForename() {
        return  forename;
    }
}
