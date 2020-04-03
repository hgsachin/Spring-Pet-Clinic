package com.hgsachin.springpetclinic.service.map;

import com.hgsachin.springpetclinic.model.Speciality;
import com.hgsachin.springpetclinic.service.SpecialitiesService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SpecialitiesServiceMap extends AbstractMapService<Speciality, Long> implements SpecialitiesService {
    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public Speciality save(Speciality t) {
        return super.save(t);
    }

    @Override
    public void delete(Speciality t) {
        super.delete(t);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
