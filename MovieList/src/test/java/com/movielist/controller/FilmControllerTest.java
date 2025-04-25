package com.movielist.controller;

import com.movielist.model.Film;
import com.movielist.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class FilmControllerTest {

    @Mock
    private FilmService filmService;

    @Mock
    private Model model;

    @InjectMocks
    private FilmController filmController;

    private Film testFilm;
    private List<Film> testFilms;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testFilm = new Film(1L, "Test Film", "Test Rendező", 2023, 120, "Akció", 8.5);
        testFilms = Arrays.asList(testFilm);
    }

    @Test
    void listFilms() {
        // Arrange
        when(filmService.findAllFilms()).thenReturn(testFilms);
        when(model.addAttribute(eq("films"), any())).thenReturn(model);
        when(model.addAttribute(eq("newFilm"), any())).thenReturn(model);

        // Act
        String viewName = filmController.listFilms(model);

        // Assert
        assertEquals("index", viewName);
        verify(model).addAttribute("films", testFilms);
        verify(model).addAttribute(eq("newFilm"), any(Film.class));
        verify(filmService).findAllFilms();
    }

    @Test
    void addFilm() {
        // Arrange
        when(filmService.saveFilm(testFilm)).thenReturn(testFilm);

        // Act
        String viewName = filmController.addFilm(testFilm);

        // Assert
        assertEquals("redirect:/", viewName);
        verify(filmService).saveFilm(testFilm);
    }

    @Test
    void searchFilms_ByCim() {
        // Arrange
        when(filmService.findByCim("Test")).thenReturn(testFilms);
        when(model.addAttribute(eq("films"), any())).thenReturn(model);
        when(model.addAttribute(eq("newFilm"), any())).thenReturn(model);

        // Act
        String viewName = filmController.searchFilms("Test", null, null, null, model);

        // Assert
        assertEquals("index", viewName);
        verify(filmService).findByCim("Test");
        verify(model).addAttribute("films", testFilms);
    }

    @Test
    void searchFilms_ByRendezo() {
        // Arrange
        when(filmService.findByRendezo("Test")).thenReturn(testFilms);
        when(model.addAttribute(eq("films"), any())).thenReturn(model);
        when(model.addAttribute(eq("newFilm"), any())).thenReturn(model);

        // Act
        String viewName = filmController.searchFilms(null, "Test", null, null, model);

        // Assert
        assertEquals("index", viewName);
        verify(filmService).findByRendezo("Test");
        verify(model).addAttribute("films", testFilms);
    }

    @Test
    void searchFilms_ByKategoria() {
        // Arrange
        when(filmService.findByKategoria("Akció")).thenReturn(testFilms);
        when(model.addAttribute(eq("films"), any())).thenReturn(model);
        when(model.addAttribute(eq("newFilm"), any())).thenReturn(model);

        // Act
        String viewName = filmController.searchFilms(null, null, "Akció", null, model);

        // Assert
        assertEquals("index", viewName);
        verify(filmService).findByKategoria("Akció");
        verify(model).addAttribute("films", testFilms);
    }

    @Test
    void searchFilms_ByMinimumErtekeles() {
        // Arrange
        when(filmService.findByMinimumErtekeles(8.0)).thenReturn(testFilms);
        when(model.addAttribute(eq("films"), any())).thenReturn(model);
        when(model.addAttribute(eq("newFilm"), any())).thenReturn(model);

        // Act
        String viewName = filmController.searchFilms(null, null, null, 8.0, model);

        // Assert
        assertEquals("index", viewName);
        verify(filmService).findByMinimumErtekeles(8.0);
        verify(model).addAttribute("films", testFilms);
    }
}
