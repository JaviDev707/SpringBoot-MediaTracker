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

    @Query("SELECT v FROM Videojuego v WHERE FUNCTION('YEAR', v.finalizacion) = :año")
    List<Videojuego> findByAñoFinalizacion(@Param("año") int año);

    @Query("SELECT v FROM Videojuego v WHERE FUNCTION('YEAR', v.finalizacion) = :año AND FUNCTION('MONTH', v.finalizacion) = :mes")
    List<Videojuego> findByMesYAñoFinalizacion(@Param("mes") int mes, @Param("año") int año);

}
