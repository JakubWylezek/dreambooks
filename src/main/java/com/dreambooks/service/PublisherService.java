package com.dreambooks.service;

import com.dreambooks.model.Book;
import com.dreambooks.model.Publisher;
import com.dreambooks.repository.PublisherRepository;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    private PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher findByName(String name) {
        return publisherRepository.findByName(name);
    }

    public void isPublisherExist(Book book, Publisher publisher) {
        Publisher new_publisher = publisherRepository.findByName(publisher.getName());

        if(new_publisher == null)
            publisherRepository.save(publisher);
        else
            book.setPublisher(new_publisher);
    }
}
