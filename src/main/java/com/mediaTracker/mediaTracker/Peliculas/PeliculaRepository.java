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

    @Query("SELECT p FROM Pelicula p WHERE FUNCTION('YEAR', p.finalizacion) = :año")
    List<Pelicula> findByAñoFinalizacion(@Param("año") int año);

    @Query("SELECT p FROM Pelicula p WHERE FUNCTION('YEAR', p.finalizacion) = :año AND FUNCTION('MONTH', p.finalizacion) = :mes")
    List<Pelicula> findByMesYAñoFinalizacion(@Param("mes") int mes, @Param("año") int año);
}
