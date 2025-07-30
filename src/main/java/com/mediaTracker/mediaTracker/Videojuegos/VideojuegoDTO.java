package com.mediaTracker.mediaTracker.Videojuegos;

import java.time.LocalDate;

public record VideojuegoDTO(

    Long id,

    String titulo,
    String plataforma,
    String genero,
    Integer año,
    
    LocalDate finalizacion,
    Float rating

) {} 