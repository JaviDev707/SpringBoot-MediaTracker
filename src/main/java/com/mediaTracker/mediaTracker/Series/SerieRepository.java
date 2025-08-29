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

    @Query("SELECT s FROM Serie s WHERE FUNCTION('YEAR', s.finalizacion) = :año")
    List<Serie> findByAñoFinalizacion(@Param("año") int año);

    @Query("SELECT s FROM Serie s WHERE FUNCTION('YEAR', s.finalizacion) = :año AND FUNCTION('MONTH', s.finalizacion) = :mes")
    List<Serie> findByMesYAñoFinalizacion(@Param("mes") int mes, @Param("año") int año);
}
