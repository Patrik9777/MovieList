package com.movielist.controller;

import com.movielist.model.Film;
import com.movielist.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/")
    public String listFilms(Model model) {
        model.addAttribute("films", filmService.findAllFilms());
        model.addAttribute("newFilm", new Film());
        return "index";
    }

    @PostMapping("/add")
    public String addFilm(@ModelAttribute Film film) {
        filmService.saveFilm(film);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Film film = filmService.findFilmById(id).orElse(null);
        model.addAttribute("film", film);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateFilm(@PathVariable Long id, @ModelAttribute Film film) {
        film.setId(id);
        filmService.saveFilm(film);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchFilms(@RequestParam(required = false) String cim,
                            @RequestParam(required = false) String rendezo,
                            @RequestParam(required = false) String kategoria,
                            @RequestParam(required = false) Double minErtekeles,
                            Model model) {
        if (cim != null && !cim.isEmpty()) {
            model.addAttribute("films", filmService.findByCim(cim));
        } else if (rendezo != null && !rendezo.isEmpty()) {
            model.addAttribute("films", filmService.findByRendezo(rendezo));
        } else if (kategoria != null && !kategoria.isEmpty()) {
            model.addAttribute("films", filmService.findByKategoria(kategoria));
        } else if (minErtekeles != null) {
            model.addAttribute("films", filmService.findByMinimumErtekeles(minErtekeles));
        } else {
            model.addAttribute("films", filmService.findAllFilms());
        }
        model.addAttribute("newFilm", new Film());
        return "index";
    }
}
