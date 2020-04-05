package com.hgsachin.springpetclinic.service.map;

import com.hgsachin.springpetclinic.model.Visit;
import com.hgsachin.springpetclinic.service.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VisitServiceMap extends AbstractMapService<Visit, Long> implements VisitService  {

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit t) {
        super.delete(t);
    }

    @Override
    public Visit save(Visit t) {
        if (t.getPet() == null || t.getPet().getOwner() == null) {
            throw new RuntimeException("Invalid Visit");
        }
        return super.save(t);
    }
}
