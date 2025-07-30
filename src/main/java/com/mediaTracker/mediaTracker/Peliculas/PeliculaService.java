package com.mediaTracker.mediaTracker.Peliculas;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PeliculaService {
    
    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository){
        this.peliculaRepository = peliculaRepository;
    }

    public List<Pelicula> mostrarTodos(){
        return peliculaRepository.findAll();
    }

    public Optional<Pelicula> filtroID(Long id){
        return peliculaRepository.findById(id);
    }

    public List<Pelicula> filtroTitulo(String titulo){
        return peliculaRepository.findByTituloIgnoreCase(titulo);
    }

    public List<Pelicula> filtroPlataforma(String plataforma){
        return peliculaRepository.findByPlataformaIgnoreCase(plataforma);
    }

    public List<Pelicula> filtroGenero(String genero){
        return peliculaRepository.findByGeneroIgnoreCase(genero);
    }

    public List<Pelicula> filtroAño(int año){
        return peliculaRepository.findByAñoFinalizacion(año);
    }

    public List<Pelicula> filtroMesAño(int mes, int año){
        return peliculaRepository.findByMesYAñoFinalizacion(mes, año);
    }

    public Pelicula crearPelicula(Pelicula pelicula){
        System.out.println("Guardando Pelicula: " + pelicula);
        return peliculaRepository.save(pelicula);
    }

    public boolean borrarPelicula(Long id) {

        if (!peliculaRepository.existsById(id)) {
            return false;
        }
        peliculaRepository.deleteById(id);
        System.out.println("===> Borrando Pelicula con ID: " + id);
        return true;
    }

    public Pelicula actualizar(Long id, PeliculaDTO dto) {

        Optional<Pelicula> optPelicula = peliculaRepository.findById(id);

        if (!optPelicula.isPresent()){
            System.out.println("===> No se ecncuentra ninguna Pelicula con ID: " + id);
            throw new NoSuchElementException("Pelicula no encontrada con ID: " + id);
        } 
        Pelicula pelicula = optPelicula.get();

        if (dto.titulo() != null) pelicula.setTitulo(dto.titulo());
        if (dto.año() != null) pelicula.setAño(dto.año());   
        if (dto.plataforma() != null) pelicula.setPlataforma(dto.plataforma());
        if (dto.genero() != null) pelicula.setGenero(dto.genero());
        if (dto.finalizacion() != null) pelicula.setFinalizacion(dto.finalizacion());
        if (dto.rating() != null) pelicula.setRating(dto.rating());

        System.out.println("===> Actualizando datos..." + pelicula);
        return peliculaRepository.save(pelicula);
    }
}
