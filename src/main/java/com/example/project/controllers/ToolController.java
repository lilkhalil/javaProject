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

import com.example.project.entity.Tool;
import com.example.project.repository.ToolsRepository;
import com.example.project.service.ToolsService;


@Controller
public class ToolController {

    private final ToolsService toolsService;

    public ToolController(ToolsRepository toolsRepository, ToolsService toolsService) {
        this.toolsService = toolsService;
    }
    
    @GetMapping("/tools")
    public String getTools(Model model) {
        Iterable<Tool> tools = toolsService.getAll();
        model.addAttribute("tools", tools);
        model.addAttribute("tool", new Tool());
        return "tools";
    }

    @PostMapping("/tools")
    public String addTool(@ModelAttribute("tool") Tool tool, Model model) {
        toolsService.saveTool(tool);
        return "redirect:/tools";
    }

    @DeleteMapping("/tools/{id}")
    public String deleteTool(@PathVariable("id") Long id, Model model) {
        toolsService.deleteTool(id);
        return "redirect:/tools";
    }

    @PutMapping("/tools/{id}")
    public String editTool(@PathVariable("id") Long id,
    @RequestParam String name,
    @RequestParam(required = false) Integer power,
    @RequestParam(required = false) String toolType, 
    Model model) {
        Optional<Tool> targetTool = toolsService.getSingle(id);
        Tool tool = targetTool.orElseThrow();
        tool.setName(name);
        tool.setPower(power);
        tool.setToolType(toolType);
        toolsService.saveTool(tool);
        return "redirect:/tools";
    }
}
