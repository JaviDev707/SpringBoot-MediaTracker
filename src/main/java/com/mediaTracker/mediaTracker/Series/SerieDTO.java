package com.mediaTracker.mediaTracker.Series;

import java.time.LocalDate;

public record SerieDTO(
    
    Long id,

    String titulo,
    String plataforma,
    String genero,
    Integer año,
    
    LocalDate finalizacion,
    Float rating
) {}
