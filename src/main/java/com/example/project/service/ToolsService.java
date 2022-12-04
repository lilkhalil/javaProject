package com.example.project.service;

import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.project.repository.ToolsRepository;
import com.example.project.entity.Tool;


@Service
public class ToolsService {
    
    private final ToolsRepository toolsRepository;

    public ToolsService(ToolsRepository toolsRepository) {
        this.toolsRepository = toolsRepository;
    }

    public boolean saveTool(Tool tool) {
        if (tool.getToolType().equals("")) {
            tool.setToolType("Не назначено");
        }
        if (tool.getPower() == null) {
            tool.setPower(0);
        }
        toolsRepository.save(tool);
        return true;
    }
    
    public boolean deleteTool(Long id) {
        toolsRepository.deleteById(id);
        return true;
    }

    public Iterable<Tool> getAll() {
        return toolsRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Optional<Tool> getSingle(Long id) {
        return toolsRepository.findById(id);
    }

}
