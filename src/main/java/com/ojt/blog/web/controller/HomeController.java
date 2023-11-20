package com.ojt.blog.web.controller;

import com.ojt.blog.web.form.PersonForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    /***
     *<h2>Register Page</h2>
     * <p>Pass Person Data To Service</p>
     *
     * @param
     * @return String
     */
    @GetMapping("/")
    public String registerPage(){

        return "home/index";
    }
}
