package com.dreambooks.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"author", "publisher", "category"})
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String coverURL;

    @Lob
    private String description;
    private BigDecimal price;
    private Integer pages;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Author author;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Publisher publisher;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;

}
