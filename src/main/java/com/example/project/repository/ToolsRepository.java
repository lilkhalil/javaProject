package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.Tool;

@Repository
public interface ToolsRepository extends JpaRepository<Tool, Long> {
}
