package br.com.muniz.integrationtests.dto.wrapper;

import br.com.muniz.integrationtests.dto.PersonDTOV1;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class PersonEmbeddedDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("people")
    private List<PersonDTOV1> people;

    public PersonEmbeddedDTO() {}

    public List<PersonDTOV1> getPeople() {
        return people;
    }

    public PersonEmbeddedDTO setPeople(List<PersonDTOV1> people) {
        this.people = people;
        return this;
    }
}
