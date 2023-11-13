package com.ojt.blog.web.controller;

import com.ojt.blog.web.form.PersonForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping("/")
    public String registerPage(){

        return "home/index";
    }
}
