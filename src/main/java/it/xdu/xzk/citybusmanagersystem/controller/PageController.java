package it.xdu.xzk.citybusmanagersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @GetMapping("/inner/{page}")
    public String showPage(@PathVariable String page) {
        return "inner/" + page;
    }
}
