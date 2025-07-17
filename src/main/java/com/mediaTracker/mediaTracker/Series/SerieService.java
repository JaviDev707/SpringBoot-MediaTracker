package com.mediaTracker.mediaTracker.Series;

import org.springframework.stereotype.Service;

@Service
public class SerieService {
    
    private final SerieRepository serieRepository;

    public SerieService(SerieRepository serieRepository){
        this.serieRepository = serieRepository;
    }

    public void mosatrarTodos(){
        serieRepository.findAll();
    }
}
