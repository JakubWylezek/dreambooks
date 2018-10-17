package com.dreambooks.repository;

import com.dreambooks.model.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
    Publisher findByName(String name);
}
