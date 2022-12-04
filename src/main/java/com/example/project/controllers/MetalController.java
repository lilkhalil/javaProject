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

import com.example.project.entity.Metal;
import com.example.project.service.MetalsService;


@Controller
public class MetalController {

    private final MetalsService metalsService;

    public MetalController(MetalsService metalsService) {
        this.metalsService = metalsService;
    }
    
    @GetMapping("/metals")
    public String getMetals(Model model) {
        Iterable<Metal> metals = metalsService.getAll();
        model.addAttribute("metals", metals);
        model.addAttribute("metal", new Metal());
        return "metals";
    }

    @PostMapping("/metals")
    public String addMetal(@ModelAttribute("metal") Metal metal) {
        metalsService.saveMetal(metal);
        return "redirect:/metals";
    }

    @DeleteMapping("/metals/{id}")
    public String deleteMetal(@PathVariable("id") Long id) {
        metalsService.deleteMetal(id);
        return "redirect:/metals";
    }

    @PutMapping("/metals/{id}")
    public String editMetal(@PathVariable("id") Long id, @RequestParam String name, @RequestParam Integer sample, @RequestParam Float density) {
        Optional<Metal> metalOptional = metalsService.getSingle(id);
        Metal metal = metalOptional.orElseThrow();
        metal.setName(name);
        metal.setSample(sample);
        metal.setDensity(density);
        metalsService.saveMetal(metal);
        return "redirect:/metals";
    }
}
