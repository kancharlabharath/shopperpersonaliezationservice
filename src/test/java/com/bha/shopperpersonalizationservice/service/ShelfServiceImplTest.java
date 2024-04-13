package com.bha.shopperpersonalizationservice.service;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import com.bha.shopperpersonalizationservice.entity.Shelf;
import com.bha.shopperpersonalizationservice.repository.ShelfRepository;

public class ShelfServiceImplTest {

    @Mock
    private ShelfRepository shelfRepository;

    @Mock
    private Logger logger;

    @InjectMocks
    private ShelfServiceImpl shelfService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetShelfByShopperId() {
        // Mock data
        String shopperId = "testShopperId";
        Shelf shelf = new Shelf();
        shelf.setShopperId(shopperId);
        Optional<Shelf> optionalShelf = Optional.of(shelf);

        // Mock repository method
        when(shelfRepository.findByShopperId(shopperId)).thenReturn(optionalShelf);

        // Call the service method
        Optional<Shelf> result = shelfService.getShelfByShopperId(shopperId);

        // Verify that the method returns the expected shelf
        Assertions.assertEquals(optionalShelf, result);
    }

    @Test
    public void testGetAllShelfShopperId() {
        // Mock data
        List<Shelf> shelves = new ArrayList<>();
        shelves.add(new Shelf());
        shelves.add(new Shelf());

        // Mock repository method
        when(shelfRepository.findAll()).thenReturn(shelves);

        // Call the service method
        List<Shelf> result = shelfService.getAllShelfShopperId();

        // Verify that the method returns the expected list of shelves
        Assertions.assertEquals(shelves, result);
    }
}

