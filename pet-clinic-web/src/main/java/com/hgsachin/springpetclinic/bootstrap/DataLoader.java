package com.hgsachin.springpetclinic.bootstrap;

import com.hgsachin.springpetclinic.model.*;
import com.hgsachin.springpetclinic.service.OwnerService;
import com.hgsachin.springpetclinic.service.PetTypeService;
import com.hgsachin.springpetclinic.service.SpecialitiesService;
import com.hgsachin.springpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialitiesService specialitiesService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialitiesService specialitiesService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialitiesService = specialitiesService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) loadInitialData();
    }

    private void loadInitialData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType dogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType catPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality radiologySpeciality = specialitiesService.save(radiology);
        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality surgerySpeciality = specialitiesService.save(surgery);
        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality dentistrySpeciality = specialitiesService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("John");
        owner1.setLastName("Doe");
        owner1.setAddress("123 Blooklyn");
        owner1.setCity("Nel Wolk");
        owner1.setTelephone("1231231231");
        Pet johnsPet = new Pet();
        johnsPet.setPetType(dogPetType);
        johnsPet.setBirthDate(LocalDate.now());
        johnsPet.setOwner(owner1);
        johnsPet.setName("Snoopy");
        owner1.getPets().add(johnsPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Reggie");
        owner2.setLastName("Johnson");
        Pet reggiesPet = new Pet();
        reggiesPet.setPetType(catPetType);
        reggiesPet.setBirthDate(LocalDate.now());
        reggiesPet.setOwner(owner2);
        reggiesPet.setName("Garfield");
        owner2.getPets().add(reggiesPet);
        ownerService.save(owner2);

        System.out.println("Loaded Owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Tom");
        vet1.setLastName("Dick");
        vet1.getSpecialities().add(radiologySpeciality);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Sam");
        vet2.setLastName("Wills");
        vet2.getSpecialities().add(surgerySpeciality);
        vet2.getSpecialities().add(dentistrySpeciality);
        vetService.save(vet2);

        System.out.println("Loaded Vets");
    }
}
