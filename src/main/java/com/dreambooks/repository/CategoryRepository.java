package com.dreambooks.repository;

import com.dreambooks.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByDescription(String description);

    @Query("SELECT COUNT(c) FROM Category c")
    Long countCategories();
}
