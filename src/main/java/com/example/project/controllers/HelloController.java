package com.example.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;


@Controller
public class HelloController {

    @GetMapping("/")
    public String getHello(Model model) {
        return "index";
    }

}
