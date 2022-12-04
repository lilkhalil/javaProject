package com.example.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import com.example.project.entity.Material;
import com.example.project.service.MaterialsService;


@Controller
public class MaterialController {

    private final MaterialsService materialsService;

    public MaterialController(MaterialsService materialsService) {
        this.materialsService = materialsService;
    }
    
    @GetMapping("/materials")
    public String getMaterials(Model model) {
        Iterable<Material> materials = materialsService.getAll();
        model.addAttribute("materials", materials);
        model.addAttribute("material", new Material());
        return "materials";
    }

    @PostMapping("/materials")
    public String addMaterial(@ModelAttribute("material") Material material) {
        materialsService.saveMaterial(material);
        return "redirect:/materials";
    }

    @DeleteMapping("/materials/{id}")
    public String deleteMaterial(@PathVariable("id") Long id) {
        materialsService.deleteMaterial(id);
        return "redirect:/materials";
    }

    @PutMapping("/materials/{id}")
    public String editMaterial(@PathVariable("id") Long id, @RequestParam String name, @RequestParam Integer resistance, @RequestParam(required = false) String type) {
        Optional<Material> materialOptional = materialsService.getSingle(id);
        Material material = materialOptional.orElseThrow();
        material.setName(name);
        material.setResistance(resistance);
        material.setType(type);
        materialsService.saveMaterial(material);
        return "redirect:/materials";
    }
}
