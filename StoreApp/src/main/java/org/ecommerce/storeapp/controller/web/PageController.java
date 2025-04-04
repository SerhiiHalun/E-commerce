package org.ecommerce.storeapp.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {
    @GetMapping("/")
    public String ShowHomePage(){
        return "redirect:product/home";
    }
}
