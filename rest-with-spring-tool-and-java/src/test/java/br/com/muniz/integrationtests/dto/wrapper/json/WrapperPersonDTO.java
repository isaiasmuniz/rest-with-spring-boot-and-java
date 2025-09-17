package br.com.muniz.integrationtests.dto.wrapper.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class WrapperPersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("_embedded")
    private PersonEmbeddedDTO embedded;

    public WrapperPersonDTO() {}

    public PersonEmbeddedDTO getembedded() {
        return embedded;
    }

    public WrapperPersonDTO setembedded(PersonEmbeddedDTO embedded) {
        this.embedded = embedded;
        return this;
    }
}
