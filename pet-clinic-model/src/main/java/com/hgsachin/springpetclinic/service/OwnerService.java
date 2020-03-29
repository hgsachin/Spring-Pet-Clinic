package com.hgsachin.springpetclinic.service;

import com.hgsachin.springpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
