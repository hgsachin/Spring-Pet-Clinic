package com.hgsachin.springpetclinic.repository;

import com.hgsachin.springpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
