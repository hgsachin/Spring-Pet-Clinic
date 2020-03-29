package com.hgsachin.springpetclinic.bootstrap;

import com.hgsachin.springpetclinic.model.Owner;
import com.hgsachin.springpetclinic.model.Vet;
import com.hgsachin.springpetclinic.service.OwnerService;
import com.hgsachin.springpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("John");
        owner1.setLastName("Doe");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Reggie");
        owner2.setLastName("Johnson");
        ownerService.save(owner2);

        System.out.println("Loaded Owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Tom");
        vet1.setLastName("Dick");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Sam");
        vet2.setLastName("Wills");
        vetService.save(vet2);


        System.out.println("Loaded Vets");

    }
}
