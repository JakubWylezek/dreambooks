package com.dreambooks.repository;

import com.dreambooks.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    @Query("SELECT COUNT(u) FROM User u")
    Long countUsers();

    @Query(value = "SELECT * FROM User ORDER BY id DESC limit 5", nativeQuery = true)
    Set<User> getMaxFiveNewUsers();

    @Query(value = "SELECT u FROM User u WHERE u.lastName LIKE concat('%',:name,'%') OR u.firstName LIKE concat('%',:name,'%')")
    Set<User> findUsersWithPartOfNames(@Param("name") String name);
}