package com.blackwell;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Value("${url.api}")
    private String apiUrl;
    @Value("${url.js}")
    private String jsUrl;

    @GetMapping(value = {"/", "/dox/**", "/profile/**"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("apiUrl", apiUrl);
        modelAndView.addObject("jsUrl", jsUrl);
        return modelAndView;
    }
}
