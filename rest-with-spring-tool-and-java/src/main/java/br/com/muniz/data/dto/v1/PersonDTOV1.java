package br.com.muniz.data.dto.v1;

import java.io.Serializable;
import java.util.Objects;

public class PersonDTOV1 implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;
    private String firstName;
    private String lastName;
    private String adress;
    private String gender;

    public PersonDTOV1() {}

    public PersonDTOV1(String firstName, long id, String gender, String adress, String lastName) {
        this.firstName = firstName;
        this.id = id;
        this.gender = gender;
        this.adress = adress;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PersonDTOV1 person)) return false;
        return id == person.id && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(adress, person.adress) && Objects.equals(gender, person.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, adress, gender);
    }
}

