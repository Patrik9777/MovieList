package com.movielist.repository;

import com.movielist.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findByRendezoContainingIgnoreCase(String rendezo);
    List<Film> findByKategoriaContainingIgnoreCase(String kategoria);
    List<Film> findByCimContainingIgnoreCase(String cim);
    List<Film> findByErtekelesGreaterThanEqual(double ertekeles);
}
