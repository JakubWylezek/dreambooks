package com.dreambooks.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Book> books;


}
