package com.example.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import com.example.project.entity.Gem;
import com.example.project.entity.Jewelry;
import com.example.project.entity.Metal;
import com.example.project.service.GemsService;
import com.example.project.service.JewelrysService;
import com.example.project.service.MetalsService;


@Controller
public class JewelryController {

    private final JewelrysService jewelrysService;
    private final GemsService gemsService;
    private final MetalsService metalsService;

    public JewelryController(JewelrysService jewelrysService, GemsService gemsService, MetalsService metalsService) {
        this.jewelrysService = jewelrysService;
        this.gemsService = gemsService;
        this.metalsService = metalsService;
    }
    
    @GetMapping("/jewelrys")
    public String getJewelrys(Model model) {
        Iterable<Jewelry> jewelrys = jewelrysService.getAll();
        Iterable<Gem> gems = gemsService.getAll();
        Iterable<Metal> metals = metalsService.getAll(); 
        model.addAttribute("jewelrys",jewelrys);
        model.addAttribute("gems", gems);
        model.addAttribute("metals", metals);
        return "jewelrys";
    }

    @PostMapping("/jewelrys")
    public String addJewelry(@RequestParam String type, @RequestParam Integer cost, @RequestParam Long gemId, @RequestParam Long metalId) {
        Gem gem = gemsService.getSingle(gemId).orElseThrow();
        Metal metal = metalsService.getSingle(metalId).orElseThrow();
        jewelrysService.saveJewelry(new Jewelry(type, cost, gem, metal));
        return "redirect:/jewelrys";
    }

    @DeleteMapping("/jewelrys/{id}")
    public String deleteJewelry(@PathVariable("id") Long id) {
        jewelrysService.deleteJewelry(id);
        return "redirect:/jewelrys";
    }

    @PutMapping("/jewelrys/{id}")
    public String editJewelry(@PathVariable("id") Long id, @RequestParam String type, @RequestParam Integer cost, @RequestParam Long gemId, @RequestParam Long metalId) {
        Optional<Jewelry> jewelryOptional = jewelrysService.getSingle(id);
        Jewelry jewelry = jewelryOptional.orElseThrow();
        jewelry.setType(type);
        jewelry.setCost(cost);
        jewelry.setGem(gemsService.getSingle(gemId).orElseThrow());
        jewelry.setMetal(metalsService.getSingle(metalId).orElseThrow());
        jewelrysService.saveJewelry(jewelry);
        return "redirect:/jewelrys";
    }

}
