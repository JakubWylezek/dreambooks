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

    public Publisher findByName(String name) {
        return publisherRepository.findByName(name);
    }

    public void isPublisherExist(Book book, Publisher publisher) {
        Publisher new_publisher = findByName(publisher.getName());

        if(new_publisher == null)
            publisherRepository.save(publisher);
        else
            book.setPublisher(new_publisher);
    }
}
