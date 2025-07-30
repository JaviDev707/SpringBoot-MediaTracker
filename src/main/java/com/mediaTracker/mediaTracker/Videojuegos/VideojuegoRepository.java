package com.mediaTracker.mediaTracker.Videojuegos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface VideojuegoRepository extends JpaRepository<Videojuego, Long>{
    
    List<Videojuego> findByTituloIgnoreCase(String titulo);
    List<Videojuego> findByGeneroIgnoreCase(String genero);
    List<Videojuego> findByPlataformaIgnoreCase(String plataforma);

    @Query("SELECT l FROM Libro l WHERE FUNCTION('YEAR', l.finalizacion) = :año")
    List<Videojuego> findByAñoFinalizacion(@Param("año") int año);

    @Query("SELECT l FROM Libro l WHERE FUNCTION('YEAR', l.finalizacion) = :año AND FUNCTION('MONTH', l.finalizacion) = :mes")
    List<Videojuego> findByMesYAñoFinalizacion(@Param("mes") int mes, @Param("año") int año);

}
