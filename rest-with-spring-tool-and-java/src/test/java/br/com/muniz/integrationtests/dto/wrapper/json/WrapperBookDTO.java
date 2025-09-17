package br.com.muniz.integrationtests.dto.wrapper.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class WrapperBookDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("_embedded")
    private BookEmbeddedDTO embedded;

    public WrapperBookDTO() {}

    public BookEmbeddedDTO getembedded() {
        return embedded;
    }

    public WrapperBookDTO setembedded(BookEmbeddedDTO embedded) {
        this.embedded = embedded;
        return this;
    }
}
