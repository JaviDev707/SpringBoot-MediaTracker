package com.mediaTracker.mediaTracker.Peliculas;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*") // Para pruebas locales, eliminar luego!!
@RestController
@RequestMapping("/pelicula")
public class PeliculaController {
    
    private final PeliculaService peliculaService; 

    public PeliculaController(PeliculaService peliculaService){
        this.peliculaService = peliculaService;
    }

    @GetMapping("/todos")
    public List<Pelicula> listarTodos(){
        return peliculaService.mostrarTodos();
    }

    @GetMapping("/id/{id}")
    public Optional<Pelicula> filtrarID(@PathVariable Long id){
        return peliculaService.filtroID(id);
    }
    
    @GetMapping("/titulo/{titulo}")
    public List<Pelicula> filtrarTitulo(@PathVariable String titulo){
        return peliculaService.filtroTitulo(titulo);
    }

    @GetMapping("/plataforma/{plataforma}")
    public List<Pelicula> filtrarAutor(@PathVariable String plataforma){
        return peliculaService.filtroPlataforma(plataforma);
    }

    @GetMapping("/genero/{genero}")
    public List<Pelicula> filtrarGenero(@PathVariable String genero){
        return peliculaService.filtroGenero(genero);
    }

    @GetMapping("/año/{año}")
    public List<Pelicula> filtrarAño(@PathVariable int año){
        return peliculaService.filtroAño(año);
    }

    @GetMapping("/mes_año/{mes}/{año}")
    public List<Pelicula> filtrarMesAño(@PathVariable int mes, @PathVariable int año){
        return peliculaService.filtroMesAño(mes, año);
    }

    @PostMapping("/registro")
    public ResponseEntity<Pelicula> CreatePelicula(@RequestBody Pelicula pelicula){
        Pelicula nuevo = peliculaService.crearPelicula(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> deletePelicula(@PathVariable Long id){
        boolean eliminado = peliculaService.borrarPelicula(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build(); //404 Not found
        }
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PatchMapping("/actualizar/{id}")
    public ResponseEntity<Pelicula> updatePelicula(@PathVariable Long id, @RequestBody PeliculaDTO dto){
        try {
            Pelicula actualizado = peliculaService.actualizar(id, dto);
            return ResponseEntity.ok(actualizado); // 200 OK
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
