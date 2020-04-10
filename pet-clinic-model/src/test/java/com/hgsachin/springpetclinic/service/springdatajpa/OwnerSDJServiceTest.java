package com.hgsachin.springpetclinic.service.springdatajpa;

import com.hgsachin.springpetclinic.model.Owner;
import com.hgsachin.springpetclinic.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OwnerSDJServiceTest {

    private final String LAST_NAME = "Frank";

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJService service;

    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        Mockito.when(ownerRepository.findByLastName(any())).thenReturn(owner);

        Owner fetchedOwner = service.findByLastName(LAST_NAME);
        assertNotNull(fetchedOwner);
        assertEquals(LAST_NAME, fetchedOwner.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}