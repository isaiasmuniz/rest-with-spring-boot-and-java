package br.com.muniz.data.dto.booksdto;

import jakarta.persistence.Column;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Relation(collectionRelation = "book")
public class BooksDTOV1 extends RepresentationModel<BooksDTOV1> implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;
    private String authorName;
    private Date launchData;
    private double price;
    private String title;

    public BooksDTOV1() {}

    public BooksDTOV1(long id, String authorName, Date launchData, double price, String title) {
        this.id = id;
        this.authorName = authorName;
        this.launchData = launchData;
        this.price = price;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getLaunchData() {
        return launchData;
    }

    public void setLaunchData(Date launchData) {
        this.launchData = launchData;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BooksDTOV1 that)) return false;
        if (!super.equals(o)) return false;
        return id == that.id && Double.compare(price, that.price) == 0 && Objects.equals(authorName, that.authorName) && Objects.equals(launchData, that.launchData) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, authorName, launchData, price, title);
    }
}
