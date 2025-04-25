package com.movielist.service;

import com.movielist.model.Film;
import com.movielist.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {
    
    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> findAllFilms() {
        return filmRepository.findAll();
    }

    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }

    public Optional<Film> findFilmById(Long id) {
        return filmRepository.findById(id);
    }

    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }

    public List<Film> findByRendezo(String rendezo) {
        return filmRepository.findByRendezoContainingIgnoreCase(rendezo);
    }

    public List<Film> findByKategoria(String kategoria) {
        return filmRepository.findByKategoriaContainingIgnoreCase(kategoria);
    }

    public List<Film> findByCim(String cim) {
        return filmRepository.findByCimContainingIgnoreCase(cim);
    }

    public List<Film> findByMinimumErtekeles(double ertekeles) {
        return filmRepository.findByErtekelesGreaterThanEqual(ertekeles);
    }
}
