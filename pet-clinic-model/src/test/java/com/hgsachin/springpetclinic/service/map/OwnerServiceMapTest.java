package com.hgsachin.springpetclinic.service.map;

import com.hgsachin.springpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerServiceMapTest {

    private OwnerServiceMap ownerServiceMap;
    private final Long id1 = 1L, id2 = 2L;
    private final String lastName = "Harry";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(id1).lastName(lastName).build());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(id1);
        assertEquals(id1, owner.getId());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(id1);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(id1));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void saveWithId() {
        Owner savedOwner = ownerServiceMap.save(Owner.builder().id(id2).build());
        assertEquals(2, ownerServiceMap.findAll().size());
        assertEquals(id2, savedOwner.getId());
    }

    @Test
    void saveWithoutId() {
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findByLastName() {
        Owner harry = ownerServiceMap.findByLastName(lastName);
        assertNotNull(harry);
        assertEquals(lastName, harry.getLastName());
    }
}