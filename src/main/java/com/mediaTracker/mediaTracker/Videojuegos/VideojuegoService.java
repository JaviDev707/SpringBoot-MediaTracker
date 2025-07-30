package com.mediaTracker.mediaTracker.Videojuegos;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class VideojuegoService {
    
    private final VideojuegoRepository videojuegoRepository;

    public VideojuegoService(VideojuegoRepository videojuegoRepository){
        this.videojuegoRepository = videojuegoRepository;
    }

    public List<Videojuego> mostrarTodos(){
        return videojuegoRepository.findAll();
    }

    public Optional<Videojuego> filtroID(Long id){
        return videojuegoRepository.findById(id);
    }

    public List<Videojuego> filtroTitulo(String titulo){
        return videojuegoRepository.findByTituloIgnoreCase(titulo);
    }

    public List<Videojuego> filtroPlataforma(String plataforma){
        return videojuegoRepository.findByPlataformaIgnoreCase(plataforma);
    }

    public List<Videojuego> filtroGenero(String genero){
        return videojuegoRepository.findByGeneroIgnoreCase(genero);
    }

    public List<Videojuego> filtroAño(int año){
        return videojuegoRepository.findByAñoFinalizacion(año);
    }

    public List<Videojuego> filtroMesAño(int mes, int año){
        return videojuegoRepository.findByMesYAñoFinalizacion(mes, año);
    }

    public Videojuego crearLibro(Videojuego videojuego){
        System.out.println("Guardando Videojuego: " + videojuego);
        return videojuegoRepository.save(videojuego);
    }

    public boolean borrarLibro(Long id) {

        if (!videojuegoRepository.existsById(id)) {
            return false;
        }
        videojuegoRepository.deleteById(id);
        System.out.println("===> Borrando Videojuego con ID: " + id);
        return true;
    }

    public Videojuego actualizar(Long id, VideojuegoDTO dto) {

        Optional<Videojuego> optVideojuego = videojuegoRepository.findById(id);

        if (!optVideojuego.isPresent()){
            System.out.println("===> No se ecncuentra ningun Videojuego con ID: " + id);
            throw new NoSuchElementException("Videojuego no encontrado con ID: " + id);
        } 
        Videojuego videojuego = optVideojuego.get();

        if (dto.titulo() != null) videojuego.setTitulo(dto.titulo());
        if (dto.año() != null) videojuego.setAño(dto.año());   
        if (dto.plataforma() != null) videojuego.setPlataforma(dto.plataforma());
        if (dto.genero() != null) videojuego.setGenero(dto.genero());
        if (dto.finalizacion() != null) videojuego.setFinalizacion(dto.finalizacion());
        if (dto.rating() != null) videojuego.setRating(dto.rating());

        System.out.println("===> Actualizando datos..." + videojuego);
        return videojuegoRepository.save(videojuego);
    }
}
