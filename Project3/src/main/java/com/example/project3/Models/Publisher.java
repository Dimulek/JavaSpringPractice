package com.example.project3.Models;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, unique = true)
    @NotBlank(message = "Введите Название публикатора")
    @Size(min=2, message = "Название публикатора не может быть меньше 2 букв")
    @Size(max=100, message = "Название публикатора не может быть больше 100 букв")
    private String  name;

    public Publisher() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
