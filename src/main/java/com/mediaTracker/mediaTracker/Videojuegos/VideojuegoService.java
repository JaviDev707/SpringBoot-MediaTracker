package com.mediaTracker.mediaTracker.Videojuegos;

import org.springframework.stereotype.Service;

@Service
public class VideojuegoService {
    
    private final VideojuegoRepository videojuegoRepository;

    public VideojuegoService(VideojuegoRepository videojuegoRepository){
        this.videojuegoRepository = videojuegoRepository;
    }

    public void mosatrarTodos(){
        videojuegoRepository.findAll();
    }
}
