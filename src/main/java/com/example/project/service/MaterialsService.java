package com.example.project.service;

import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.project.entity.Material;
import com.example.project.repository.MaterialsRepository;


@Service
public class MaterialsService {
    
    private final MaterialsRepository materialsRepository;

    public MaterialsService(MaterialsRepository materialsRepository) {
        this.materialsRepository = materialsRepository;
    }

    public boolean saveMaterial(Material material) {
        materialsRepository.save(material);
        return true;
    }

    public boolean deleteMaterial(Long id) {
        materialsRepository.deleteById(id);
        return true;
    }

    public Iterable<Material> getAll() {
        return materialsRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Optional<Material> getSingle(Long id) {
        return materialsRepository.findById(id);
    }

}
