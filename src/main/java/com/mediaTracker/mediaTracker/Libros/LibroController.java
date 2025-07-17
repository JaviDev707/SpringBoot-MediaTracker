package com.mediaTracker.mediaTracker.Libros;

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
@RequestMapping("/libro")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService){
        this.libroService = libroService;
    }

    @GetMapping("/todos")
    public List<Libro> listarTodos(){
        return libroService.mostrarTodos();
    }

    @GetMapping("/id/{id}")
    public Optional<Libro> filtrarID(@PathVariable Long id){
        return libroService.filtroID(id);
    }
    
    @GetMapping("/titulo/{titulo}")
    public List<Libro> filtrarTitulo(@PathVariable String titulo){
        return libroService.filtroTitulo(titulo);
    }

    @GetMapping("/autor/{autor}")
    public List<Libro> filtrarAutor(@PathVariable String autor){
        return libroService.filtroAutor(autor);
    }

    @GetMapping("/genero/{genero}")
    public List<Libro> filtrarGenero(@PathVariable String genero){
        return libroService.filtroGenero(genero);
    }

    @GetMapping("/año/{año}")
    public List<Libro> filtrarAño(@PathVariable int año){
        return libroService.filtroAño(año);
    }

    @GetMapping("/mes_año/{mes}/{año}")
    public List<Libro> filtrarMesAño(@PathVariable int mes, @PathVariable int año){
        return libroService.filtroMesAño(mes, año);
    }

    @PostMapping("/registro")
    public ResponseEntity<Libro> CreateLibro(@RequestBody Libro libro){
        Libro nuevo = libroService.crearLibro(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id){
        boolean eliminado = libroService.borrarLibro(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build(); //404 Not found
        }
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PatchMapping("/actualizar/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody LibroDTO dto){
        try {
            Libro actualizado = libroService.actualizar(id, dto);
            return ResponseEntity.ok(actualizado); // 200 OK
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
    
}
