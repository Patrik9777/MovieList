package com.movielist.service;

import com.movielist.model.Film;
import com.movielist.repository.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FilmServiceTest {

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private FilmService filmService;

    private Film testFilm;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testFilm = new Film(1L, "Test Film", "Test Rendező", 2023, 120, "Akció", 8.5);
    }

    @Test
    void findAllFilms() {
        // Arrange
        List<Film> expectedFilms = Arrays.asList(
            testFilm,
            new Film(2L, "Másik Film", "Másik Rendező", 2022, 110, "Vígjáték", 7.5)
        );
        when(filmRepository.findAll()).thenReturn(expectedFilms);

        // Act
        List<Film> actualFilms = filmService.findAllFilms();

        // Assert
        assertEquals(expectedFilms.size(), actualFilms.size());
        assertEquals(expectedFilms.get(0).getCim(), actualFilms.get(0).getCim());
        verify(filmRepository, times(1)).findAll();
    }

    @Test
    void saveFilm() {
        // Arrange
        when(filmRepository.save(any(Film.class))).thenReturn(testFilm);

        // Act
        Film savedFilm = filmService.saveFilm(testFilm);

        // Assert
        assertNotNull(savedFilm);
        assertEquals(testFilm.getCim(), savedFilm.getCim());
        verify(filmRepository, times(1)).save(testFilm);
    }

    @Test
    void findFilmById() {
        // Arrange
        when(filmRepository.findById(1L)).thenReturn(Optional.of(testFilm));

        // Act
        Optional<Film> foundFilm = filmService.findFilmById(1L);

        // Assert
        assertTrue(foundFilm.isPresent());
        assertEquals(testFilm.getCim(), foundFilm.get().getCim());
        verify(filmRepository, times(1)).findById(1L);
    }

    @Test
    void deleteFilm() {
        // Arrange
        doNothing().when(filmRepository).deleteById(1L);

        // Act
        filmService.deleteFilm(1L);

        // Assert
        verify(filmRepository, times(1)).deleteById(1L);
    }

    @Test
    void findByRendezo() {
        // Arrange
        List<Film> expectedFilms = Arrays.asList(testFilm);
        when(filmRepository.findByRendezoContainingIgnoreCase("Test")).thenReturn(expectedFilms);

        // Act
        List<Film> foundFilms = filmService.findByRendezo("Test");

        // Assert
        assertFalse(foundFilms.isEmpty());
        assertEquals(testFilm.getRendezo(), foundFilms.get(0).getRendezo());
        verify(filmRepository, times(1)).findByRendezoContainingIgnoreCase("Test");
    }

    @Test
    void findByKategoria() {
        // Arrange
        List<Film> expectedFilms = Arrays.asList(testFilm);
        when(filmRepository.findByKategoriaContainingIgnoreCase("Akció")).thenReturn(expectedFilms);

        // Act
        List<Film> foundFilms = filmService.findByKategoria("Akció");

        // Assert
        assertFalse(foundFilms.isEmpty());
        assertEquals(testFilm.getKategoria(), foundFilms.get(0).getKategoria());
        verify(filmRepository, times(1)).findByKategoriaContainingIgnoreCase("Akció");
    }

    @Test
    void findByCim() {
        // Arrange
        List<Film> expectedFilms = Arrays.asList(testFilm);
        when(filmRepository.findByCimContainingIgnoreCase("Test")).thenReturn(expectedFilms);

        // Act
        List<Film> foundFilms = filmService.findByCim("Test");

        // Assert
        assertFalse(foundFilms.isEmpty());
        assertEquals(testFilm.getCim(), foundFilms.get(0).getCim());
        verify(filmRepository, times(1)).findByCimContainingIgnoreCase("Test");
    }

    @Test
    void findByMinimumErtekeles() {
        // Arrange
        List<Film> expectedFilms = Arrays.asList(testFilm);
        when(filmRepository.findByErtekelesGreaterThanEqual(8.0)).thenReturn(expectedFilms);

        // Act
        List<Film> foundFilms = filmService.findByMinimumErtekeles(8.0);

        // Assert
        assertFalse(foundFilms.isEmpty());
        assertTrue(foundFilms.get(0).getErtekeles() >= 8.0);
        verify(filmRepository, times(1)).findByErtekelesGreaterThanEqual(8.0);
    }
}
