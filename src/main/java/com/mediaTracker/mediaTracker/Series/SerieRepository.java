package com.mediaTracker.mediaTracker.Series;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long>{
    
    List<Serie> findByTituloIgnoreCase(String titulo);
    List<Serie> findByGeneroIgnoreCase(String genero);
    List<Serie> findByPlataformaIgnoreCase(String plataforma);

    @Query("SELECT l FROM Libro l WHERE FUNCTION('YEAR', l.finalizacion) = :año")
    List<Serie> findByAñoFinalizacion(@Param("año") int año);

    @Query("SELECT l FROM Libro l WHERE FUNCTION('YEAR', l.finalizacion) = :año AND FUNCTION('MONTH', l.finalizacion) = :mes")
    List<Serie> findByMesYAñoFinalizacion(@Param("mes") int mes, @Param("año") int año);
}
