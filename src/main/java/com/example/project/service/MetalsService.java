package com.example.project.service;

import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.project.entity.Metal;
import com.example.project.repository.MetalsRepository;


@Service
public class MetalsService {
    
    private final MetalsRepository metalsRepository;

    public MetalsService(MetalsRepository metalsRepository) {
        this.metalsRepository = metalsRepository;
    }

    public boolean saveMetal(Metal metal) {
        metalsRepository.save(metal);
        return true;
    }

    public boolean deleteMetal(Long id) {
        metalsRepository.deleteById(id);
        return true;
    }

    public Iterable<Metal> getAll() {
        return metalsRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Optional<Metal> getSingle(Long id) {
        return metalsRepository.findById(id);
    }
    
}
