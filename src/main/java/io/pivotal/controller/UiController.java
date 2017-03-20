package io.pivotal.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@Controller
class UiController {
    
    @GetMapping("/ui")
    public String index(Model model) {
        //model.addAttribute("user", authentication.getName());

        /* webapp/index.ftl */
        return "index";
    }
}
