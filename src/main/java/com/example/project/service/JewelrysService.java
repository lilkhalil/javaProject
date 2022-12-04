package com.example.project.service;

import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.project.entity.Jewelry;
import com.example.project.repository.JewelrysRepository;

@Service
public class JewelrysService {
    
    private final JewelrysRepository jewelrysRepository;

    public JewelrysService(JewelrysRepository jewelrysRepository) {
        this.jewelrysRepository = jewelrysRepository;
    }

    public boolean saveJewelry(Jewelry jewelry) {
        jewelrysRepository.save(jewelry);
        return true;
    }

    public boolean deleteJewelry(Long id) {
        jewelrysRepository.deleteById(id);
        return true;
    }

    public Iterable<Jewelry> getAll() {
        return jewelrysRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Optional<Jewelry> getSingle(Long id) {
        return jewelrysRepository.findById(id);
    }
}
