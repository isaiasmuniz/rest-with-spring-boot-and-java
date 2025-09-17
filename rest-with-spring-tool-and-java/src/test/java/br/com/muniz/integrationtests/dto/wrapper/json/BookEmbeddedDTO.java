package br.com.muniz.integrationtests.dto.wrapper.json;

import br.com.muniz.integrationtests.dto.BookDTOV1;
import br.com.muniz.integrationtests.dto.PersonDTOV1;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class BookEmbeddedDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("book")
    private List<BookDTOV1> book;

    public BookEmbeddedDTO() {}

    public List<BookDTOV1> getBook() {
        return book;
    }

    public BookEmbeddedDTO setBook(List<BookDTOV1> book) {
        this.book = book;
        return this;
    }
}
