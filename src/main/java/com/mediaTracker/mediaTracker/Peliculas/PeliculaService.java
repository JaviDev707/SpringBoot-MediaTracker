package com.mediaTracker.mediaTracker.Peliculas;

import org.springframework.stereotype.Service;

@Service
public class PeliculaService {
    
    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository){
        this.peliculaRepository = peliculaRepository;
    }

    public void mosatrarTodos(){
        peliculaRepository.findAll();
    }
}
