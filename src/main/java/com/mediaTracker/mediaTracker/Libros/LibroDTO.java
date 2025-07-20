package com.mediaTracker.mediaTracker.Libros;

import java.time.LocalDate;

public record LibroDTO(
    
    Long id,

    String titulo,
    String autor,
    String genero,
    Integer año,
    
    LocalDate finalizacion,
    Float rating
    ) {
    
}
