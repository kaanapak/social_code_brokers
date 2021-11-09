package com.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @PostMapping("/signUp")
    public String signUp(Model model) {

        return "signUp";
    }

    @PostMapping("/mainPage")
    public String mainPage(Model model) {

        return "mainPage";
    }
}
