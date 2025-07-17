package com.mediaTracker.mediaTracker.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // templates/index.html
    }

    @GetMapping("/libro")
    public String libros() {
        return "indexLibros"; // templates/indexLibros.html
    }
}