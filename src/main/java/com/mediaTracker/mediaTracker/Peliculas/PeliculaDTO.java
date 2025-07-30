package com.mediaTracker.mediaTracker.Peliculas;

import java.time.LocalDate;

public record PeliculaDTO(
    
    Long id,

    String titulo,
    String plataforma,
    String genero,
    Integer año,
    
    LocalDate finalizacion,
    Float rating
) {}
