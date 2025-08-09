package com.mediaTracker.mediaTracker.Videojuegos;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/videojuego")
public class VideojuegoController {
    
    private final VideojuegoService videojuegoService;

    public VideojuegoController(VideojuegoService videojuegoService){
        this.videojuegoService = videojuegoService;
    }

    @GetMapping("/todos")
    public List<Videojuego> listarTodos(){
        return videojuegoService.mostrarTodos();
    }

    @GetMapping("/id/{id}")
    public Optional<Videojuego> filtrarID(@PathVariable Long id){
        return videojuegoService.filtroID(id);
    }
    
    @GetMapping("/titulo/{titulo}")
    public List<Videojuego> filtrarTitulo(@PathVariable String titulo){
        return videojuegoService.filtroTitulo(titulo);
    }

    @GetMapping("/plataforma/{plataforma}")
    public List<Videojuego> filtrarAutor(@PathVariable String plataforma){
        return videojuegoService.filtroPlataforma(plataforma);
    }

    @GetMapping("/genero/{genero}")
    public List<Videojuego> filtrarGenero(@PathVariable String genero){
        return videojuegoService.filtroGenero(genero);
    }

    @GetMapping("/año/{año}")
    public List<Videojuego> filtrarAño(@PathVariable int año){
        return videojuegoService.filtroAño(año);
    }

    @GetMapping("/mes_año/{mes}/{año}")
    public List<Videojuego> filtrarMesAño(@PathVariable int mes, @PathVariable int año){
        return videojuegoService.filtroMesAño(mes, año);
    }

    @PostMapping("/registro")
    public ResponseEntity<Videojuego> CreateVideojuego(@RequestBody Videojuego videojuego){
        Videojuego nuevo = videojuegoService.crearLibro(videojuego);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> deleteVideojuego(@PathVariable Long id){
        boolean eliminado = videojuegoService.borrarLibro(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build(); //404 Not found
        }
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PatchMapping("/actualizar/{id}")
    public ResponseEntity<Videojuego> updateVideojuego(@PathVariable Long id, @RequestBody VideojuegoDTO dto){
        try {
            Videojuego actualizado = videojuegoService.actualizar(id, dto);
            return ResponseEntity.ok(actualizado); // 200 OK
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
