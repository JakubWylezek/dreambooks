package com.dreambooks.service;

import com.dreambooks.model.Author;
import com.dreambooks.model.Publisher;
import com.dreambooks.repository.PublisherRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PublisherServiceTest {

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherService publisherService;

    private Publisher publisher;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAllPublishers() {
        List<Publisher> publishersData = new LinkedList<>();
        publishersData.add(publisher);

        when(publisherRepository.findAll()).thenReturn(publishersData);

        Set<Publisher> publishers = publisherService.getAllPublishers();

        assertEquals(1, publishers.size());
        verify(publisherRepository, times(1)).findAll();
    }

    @Test
    public void shouldReturnPublisherByName() {
        publisher = new Publisher();
        publisher.setName("Publisher");

        when(publisherRepository.findByName("Publisher")).thenReturn(publisher);

        assertEquals(publisher, publisherService.findByName("Publisher"));
        verify(publisherRepository, times(1)).findByName("Publisher");
    }

    @Test
    public void shouldReturnNullBecauseAuthorDoesNotExist() {
        when(publisherRepository.findByName("Publisher")).thenReturn(null);

        assertNull(publisherService.findByName("Publisher"));
    }

    @Test
    public void shouldSaveTheAuthorOnlyOnce() {

        publisherRepository.save(publisher);
        verify(publisherRepository, times(1)).save(publisher);
    }
}