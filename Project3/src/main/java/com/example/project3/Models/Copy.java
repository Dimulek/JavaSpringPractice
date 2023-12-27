package com.example.project3.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Copy")
public class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull(message = "Введите стоимость экземпляра книги")
    @Max(999)
    @Min(1)
    private double cost;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Copy() {
    }

    public long getId() {
        return id;
    }

    public double getCost() {
        return cost;
    }

    public Book getBook() {
        return book;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void setId(long id) {
        this.id = id;
    }
}
