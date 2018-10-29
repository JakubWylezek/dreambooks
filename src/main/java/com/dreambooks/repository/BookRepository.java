package com.dreambooks.repository;

import com.dreambooks.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title LIKE concat('%',:title, '%') ")
    Set<Book> findBooksWithPartOfNames(@Param("title") String title);

    @Query("SELECT COUNT(b) FROM Book b")
    Long countBooks();


}

