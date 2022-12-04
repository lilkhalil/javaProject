package com.example.project.service;

import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.project.entity.Gem;
import com.example.project.repository.GemsRepository;


@Service
public class GemsService {

    private final GemsRepository gemsRepository;

    public GemsService(GemsRepository gemsRepository) {
        this.gemsRepository = gemsRepository;
    }

    public boolean saveGem(Gem gem) {
        gemsRepository.save(gem);
        return true;
    }

    public boolean deleteGem(Long id) {
        gemsRepository.deleteById(id);
        return true;
    }

    public Iterable<Gem> getAll() {
        return gemsRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Optional<Gem> getSingle(Long id) {
        return gemsRepository.findById(id);
    }

}
