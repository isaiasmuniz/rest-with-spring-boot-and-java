package br.com.muniz.integrationtests.dto;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Objects;

@XmlRootElement
public class BookDTOV1 implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String authorName;
    private Double price;
    private String title;

    public BookDTOV1() {}

    public BookDTOV1(long id, String authorName, Double price, String title) {
        this.id = id;
        this.authorName = authorName;
        this.price = price;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public BookDTOV1 setId(long id) {
        this.id = id;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public BookDTOV1 setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public BookDTOV1 setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookDTOV1 setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BookDTOV1 bookDTOV1)) return false;
        return id == bookDTOV1.id && Objects.equals(authorName, bookDTOV1.authorName) && Objects.equals(price, bookDTOV1.price) && Objects.equals(title, bookDTOV1.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorName, price, title);
    }
}

