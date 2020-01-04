package com.blackwell;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Value("${app.mode}")
    private String mode;

    @GetMapping(value = {"/", "/dox/**", "/profile/**"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("isDevMode", "dev".equals(mode));
        return modelAndView;
    }
}
