package br.com.muniz.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Books implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "author", nullable = true)
    private String authorName;

    @Column(name = "launch_date", nullable = true)
    private Date launchData;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "title", nullable = true)
    private String title;

    public Books() {}

    public Books(long id, String authorName, Date launchData, double price, String title) {
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
        if (!(o instanceof Books books)) return false;
        return id == books.id && Double.compare(price, books.price) == 0 && Objects.equals(authorName, books.authorName) && Objects.equals(launchData, books.launchData) && Objects.equals(title, books.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorName, launchData, price, title);
    }
}
