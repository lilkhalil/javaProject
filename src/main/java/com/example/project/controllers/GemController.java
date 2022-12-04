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

import com.example.project.entity.Gem;
import com.example.project.service.GemsService;


@Controller
public class GemController {

    private final GemsService gemsService;

    public GemController(GemsService gemsService) {
        this.gemsService = gemsService;
    }
    
    @GetMapping("/gems")
    public String getGems(Model model) {
        Iterable<Gem> gems = gemsService.getAll();
        model.addAttribute("gems", gems);
        model.addAttribute("gem", new Gem());
        return "gems";
    }

    @PostMapping("/gems")
    public String addGem(@ModelAttribute("gem") Gem gem) {
        gemsService.saveGem(gem);
        return "redirect:/gems";
    }

    @DeleteMapping("/gems/{id}")
    public String deleteGem(@PathVariable("id") Long id) {
        gemsService.deleteGem(id);
        return "redirect:/gems";
    }

    @PutMapping("/gems/{id}")
    public String editGem(@PathVariable("id") Long id, @RequestParam String name, @RequestParam Integer weight, @RequestParam String gemClass) {
        Optional<Gem> gemOptional = gemsService.getSingle(id);
        Gem gem = gemOptional.orElseThrow();
        gem.setName(name);
        gem.setWeight(weight);
        gem.setGemClass(gemClass);
        gemsService.saveGem(gem);
        return "redirect:/gems";
    }
}
