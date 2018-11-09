package com.dreambooks.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Length(min = 1, message = "*Please provide a title")
    private String title;

    @URL(message = "*Please provide a URL")
    @NotNull
    private String coverURL;

    @Lob
    private String description;

    @NumberFormat
    private BigDecimal isbn;

    @NumberFormat
    private Integer pages;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @Valid
    private Author author;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @Valid
    private Publisher publisher;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;

}
