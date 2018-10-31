package com.dreambooks.repository;

import com.dreambooks.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByDescription(String description);

    @Query("SELECT c FROM Category c WHERE c.description LIKE concat('%',:description, '%') ")
    Set<Category> findCategoriesWithPartOfNames(@Param("description") String description);

    @Query("SELECT COUNT(c) FROM Category c")
    Long countCategories();
}
