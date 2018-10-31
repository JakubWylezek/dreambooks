package com.dreambooks.repository;

import com.dreambooks.model.Bookmark;
import com.dreambooks.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends CrudRepository<Bookmark, Long> {

    Bookmark findByUser(User user);


}
