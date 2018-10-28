package com.dreambooks.service;

import com.dreambooks.model.Book;
import com.dreambooks.model.Publisher;
import com.dreambooks.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PublisherService {

    private PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Set<Publisher> getAllPublishers() {
        Set<Publisher> publishers = new HashSet<>();
        publisherRepository.findAll().iterator().forEachRemaining(publishers::add);

        return publishers;
    }

    public void isPublisherExist(Book book, Publisher publisher) {
        Publisher new_publisher = publisherRepository.findByName(publisher.getName());

        if(new_publisher == null)
            publisherRepository.save(publisher);

        book.setPublisher(new_publisher);
    }
}
