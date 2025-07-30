package com.mediaTracker.mediaTracker.Peliculas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{
    
    List<Pelicula> findByTituloIgnoreCase(String titulo);
    List<Pelicula> findByGeneroIgnoreCase(String genero);
    List<Pelicula> findByPlataformaIgnoreCase(String plataforma);

    @Query("SELECT l FROM Libro l WHERE FUNCTION('YEAR', l.finalizacion) = :año")
    List<Pelicula> findByAñoFinalizacion(@Param("año") int año);

    @Query("SELECT l FROM Libro l WHERE FUNCTION('YEAR', l.finalizacion) = :año AND FUNCTION('MONTH', l.finalizacion) = :mes")
    List<Pelicula> findByMesYAñoFinalizacion(@Param("mes") int mes, @Param("año") int año);
}
