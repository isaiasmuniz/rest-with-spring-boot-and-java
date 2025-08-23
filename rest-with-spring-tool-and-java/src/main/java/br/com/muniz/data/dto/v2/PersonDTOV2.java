package br.com.muniz.data.dto.v2;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PersonDTOV2 implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;
    private String firstName;
    private String lastName;
    private String adress;
    private String gender;
    private Date birthday;

    public PersonDTOV2() {
    }

    public PersonDTOV2(String firstName, long id, String gender, String adress, String lastName) {
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PersonDTOV2 that)) return false;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(adress, that.adress) && Objects.equals(gender, that.gender) && Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, adress, gender, birthday);
    }
}



