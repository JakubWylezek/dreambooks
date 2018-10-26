package com.dreambooks.repository;

import com.dreambooks.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    @Query("SELECT COUNT(u) FROM User u")
    Long countUsers();
}