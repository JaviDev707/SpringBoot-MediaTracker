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

    @GetMapping("/videojuego")
    public String videojuegos(){
        return "indexVideojuegos"; // templates/indexVideojuegos.html
    }

    @GetMapping("/pelicula")
    public String peliculas(){
        return "indexPeliculas"; // templates/indexPeliculas.html
    }

    @GetMapping("/serie")
    public String series(){
        return "indexSeries"; // templates/indexSeries.html
    }
}