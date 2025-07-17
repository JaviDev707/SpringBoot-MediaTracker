package com.mediaTracker.mediaTracker.Libros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{

    List<Libro> findByTituloIgnoreCase(String titulo);
    List<Libro> findByAutorContainingIgnoreCase(String autor);
    List<Libro> findByGeneroIgnoreCase(String genero);

    @Query("SELECT l FROM Libro l WHERE FUNCTION('YEAR', l.finalizacion) = :año")
    List<Libro> findByAñoFinalizacion(@Param("año") int año);

    @Query("SELECT l FROM Libro l WHERE FUNCTION('YEAR', l.finalizacion) = :año AND FUNCTION('MONTH', l.finalizacion) = :mes")
    List<Libro> findByMesYAñoFinalizacion(@Param("mes") int mes, @Param("año") int año);

}
