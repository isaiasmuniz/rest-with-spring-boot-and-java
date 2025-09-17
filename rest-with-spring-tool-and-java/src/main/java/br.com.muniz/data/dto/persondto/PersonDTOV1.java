package br.com.muniz.data.dto.persondto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serializable;
import java.util.Objects;


//@JsonPropertyOrder({"id, first_name, last_name, gender, adress"})
//@JsonFilter("persinFilter")
@Relation(collectionRelation = "people")
public class PersonDTOV1 extends RepresentationModel<PersonDTOV1> implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    //@JsonProperty("first_name")
    private String firstName;

    //@JsonProperty("last_name")
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;
    private String adress;
    private Boolean enabled;

    //private String sensitveData;

//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    private String phoneNumber;

//    @JsonFormat(pattern = "dd/MM/yyyy")
//    private String birthday;

    //@JsonIgnore
  //  @JsonSerialize(using = GenderSerializer.class)
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

//    public String getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(String birthday) {
//        this.birthday = birthday;
//    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getSensitveData() {
//        return sensitveData;
//    }
//
//    public void setSensitveData(String sensitveData) {
//        this.sensitveData = sensitveData;
//    }


    public Boolean getEnabled() {
        return enabled;
    }

    public PersonDTOV1 setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PersonDTOV1 that)) return false;
        if (!super.equals(o)) return false;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(adress, that.adress) && Objects.equals(enabled, that.enabled) && Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, firstName, lastName, adress, enabled, gender);
    }
}

