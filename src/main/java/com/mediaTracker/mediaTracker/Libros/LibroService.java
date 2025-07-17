package com.mediaTracker.mediaTracker.Libros;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class LibroService {
    
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository){
        this.libroRepository = libroRepository;
    }

    public List<Libro> mostrarTodos(){
        return libroRepository.findAll();
    }

    public Optional<Libro> filtroID(Long id){
        return libroRepository.findById(id);
    }

    public List<Libro> filtroTitulo(String titulo){
        return libroRepository.findByTituloIgnoreCase(titulo);
    }

    public List<Libro> filtroAutor(String autor){
        return libroRepository.findByAutorContainingIgnoreCase(autor);
    }

    public List<Libro> filtroGenero(String genero){
        return libroRepository.findByGeneroIgnoreCase(genero);
    }

    public List<Libro> filtroAño(int año){
        return libroRepository.findByAñoFinalizacion(año);
    }

    public List<Libro> filtroMesAño(int mes, int año){
        return libroRepository.findByMesYAñoFinalizacion(mes, año);
    }

    public Libro crearLibro(Libro libro){
        System.out.println("Guardando Libro: " + libro);
        return libroRepository.save(libro);
    }

    public boolean borrarLibro(Long id) {

        if (!libroRepository.existsById(id)) {
            return false;
        }
        libroRepository.deleteById(id);
        System.out.println("===> Borrando Libro con ID: " + id);
        return true;
    }

    public Libro actualizar(Long id, LibroDTO dto) {

        Optional<Libro> optLibro = libroRepository.findById(id);
        if (!optLibro.isPresent()){
            System.out.println("===> No se ecncuentra ningun libro con ID: " + id);
            throw new NoSuchElementException("Libro no encontrado con ID: " + id);
        } 
        Libro libro = optLibro.get();

        if (dto.titulo() != null) libro.setTitulo(dto.titulo());
        if (dto.año() != null) libro.setAño(dto.año());   
        if (dto.autor() != null) libro.setAutor(dto.autor());
        if (dto.genero() != null) libro.setGenero(dto.genero());
        if (dto.finalizacion() != null) libro.setFinalizacion(dto.finalizacion());

        System.out.println("===> Actualizando datos..." + libro);
        return libroRepository.save(libro);
    }
}
