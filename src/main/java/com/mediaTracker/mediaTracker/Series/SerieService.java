package com.mediaTracker.mediaTracker.Series;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class SerieService {
    
    private final SerieRepository serieRepository;

    public SerieService(SerieRepository serieRepository){
        this.serieRepository = serieRepository;
    }

    public List<Serie> mostrarTodos(){
        return serieRepository.findAll();
    }

    public Optional<Serie> filtroID(Long id){
        return serieRepository.findById(id);
    }

    public List<Serie> filtroTitulo(String titulo){
        return serieRepository.findByTituloIgnoreCase(titulo);
    }

    public List<Serie> filtroPlataforma(String plataforma){
        return serieRepository.findByPlataformaIgnoreCase(plataforma);
    }

    public List<Serie> filtroGenero(String genero){
        return serieRepository.findByGeneroIgnoreCase(genero);
    }

    public List<Serie> filtroAño(int año){
        return serieRepository.findByAñoFinalizacion(año);
    }

    public List<Serie> filtroMesAño(int mes, int año){
        return serieRepository.findByMesYAñoFinalizacion(mes, año);
    }

    public Serie crearSerie(Serie serie){
        System.out.println("Guardando Serie: " + serie);
        return serieRepository.save(serie);
    }

    public boolean borrarSerie(Long id) {

        if (!serieRepository.existsById(id)) {
            return false;
        }
        serieRepository.deleteById(id);
        System.out.println("===> Borrando Serie con ID: " + id);
        return true;
    }

    public Serie actualizar(Long id, SerieDTO dto) {

        Optional<Serie> optSerie = serieRepository.findById(id);

        if (!optSerie.isPresent()){
            System.out.println("===> No se ecncuentra ningun Videojuego con ID: " + id);
            throw new NoSuchElementException("Videojuego no encontrado con ID: " + id);
        } 
        Serie serie = optSerie.get();

        if (dto.titulo() != null) serie.setTitulo(dto.titulo());
        if (dto.año() != null) serie.setAño(dto.año());   
        if (dto.plataforma() != null) serie.setPlataforma(dto.plataforma());
        if (dto.genero() != null) serie.setGenero(dto.genero());
        if (dto.finalizacion() != null) serie.setFinalizacion(dto.finalizacion());
        if (dto.rating() != null) serie.setRating(dto.rating());

        System.out.println("===> Actualizando datos..." + serie);
        return serieRepository.save(serie);
    }
}
