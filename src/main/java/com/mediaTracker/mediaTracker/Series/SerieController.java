package com.mediaTracker.mediaTracker.Series;

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
@RequestMapping("/serie")
public class SerieController {
    
    private final SerieService serieService;

    public SerieController(SerieService serieService){
        this.serieService = serieService;
    }

    @GetMapping("/todos")
    public List<Serie> listarTodos(){
        return serieService.mostrarTodos();
    }

    @GetMapping("/id/{id}")
    public Optional<Serie> filtrarID(@PathVariable Long id){
        return serieService.filtroID(id);
    }
    
    @GetMapping("/titulo/{titulo}")
    public List<Serie> filtrarTitulo(@PathVariable String titulo){
        return serieService.filtroTitulo(titulo);
    }

    @GetMapping("/plataforma/{plataforma}")
    public List<Serie> filtrarAutor(@PathVariable String plataforma){
        return serieService.filtroPlataforma(plataforma);
    }

    @GetMapping("/genero/{genero}")
    public List<Serie> filtrarGenero(@PathVariable String genero){
        return serieService.filtroGenero(genero);
    }

    @GetMapping("/año/{año}")
    public List<Serie> filtrarAño(@PathVariable int año){
        return serieService.filtroAño(año);
    }

    @GetMapping("/mes_año/{mes}/{año}")
    public List<Serie> filtrarMesAño(@PathVariable int mes, @PathVariable int año){
        return serieService.filtroMesAño(mes, año);
    }

    @PostMapping("/registro")
    public ResponseEntity<Serie> CreateSerie(@RequestBody Serie serie){
        Serie nuevo = serieService.crearSerie(serie);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> deleteSerie(@PathVariable Long id){
        boolean eliminado = serieService.borrarSerie(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build(); //404 Not found
        }
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PatchMapping("/actualizar/{id}")
    public ResponseEntity<Serie> updateSerie(@PathVariable Long id, @RequestBody SerieDTO dto){
        try {
            Serie actualizado = serieService.actualizar(id, dto);
            return ResponseEntity.ok(actualizado); // 200 OK
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
