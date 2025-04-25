package com.movielist.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FilmTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        String cim = "Test Film";
        String rendezo = "Test Rendező";
        int ev = 2023;
        int hossz = 120;
        String kategoria = "Akció";
        double ertekeles = 8.5;

        // Act
        Film film = new Film(id, cim, rendezo, ev, hossz, kategoria, ertekeles);

        // Assert
        assertEquals(id, film.getId());
        assertEquals(cim, film.getCim());
        assertEquals(rendezo, film.getRendezo());
        assertEquals(ev, film.getEv());
        assertEquals(hossz, film.getHossz());
        assertEquals(kategoria, film.getKategoria());
        assertEquals(ertekeles, film.getErtekeles());
    }

    @Test
    void testSetters() {
        // Arrange
        Film film = new Film();
        Long id = 1L;
        String cim = "Test Film";
        String rendezo = "Test Rendező";
        int ev = 2023;
        int hossz = 120;
        String kategoria = "Akció";
        double ertekeles = 8.5;

        // Act
        film.setId(id);
        film.setCim(cim);
        film.setRendezo(rendezo);
        film.setEv(ev);
        film.setHossz(hossz);
        film.setKategoria(kategoria);
        film.setErtekeles(ertekeles);

        // Assert
        assertEquals(id, film.getId());
        assertEquals(cim, film.getCim());
        assertEquals(rendezo, film.getRendezo());
        assertEquals(ev, film.getEv());
        assertEquals(hossz, film.getHossz());
        assertEquals(kategoria, film.getKategoria());
        assertEquals(ertekeles, film.getErtekeles());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Film film1 = new Film(1L, "Test Film", "Test Rendező", 2023, 120, "Akció", 8.5);
        Film film2 = new Film(1L, "Test Film", "Test Rendező", 2023, 120, "Akció", 8.5);
        Film film3 = new Film(2L, "Másik Film", "Másik Rendező", 2022, 110, "Vígjáték", 7.5);

        // Assert
        assertEquals(film1, film1); // Same object
        assertEquals(film1, film2); // Equal objects
        assertNotEquals(film1, film3); // Different objects
        assertNotEquals(film1, null); // Null comparison
        assertNotEquals(film1, "Not a Film"); // Different type

        // Test each field's impact on equals
        Film filmDiffId = new Film(2L, "Test Film", "Test Rendező", 2023, 120, "Akció", 8.5);
        Film filmDiffCim = new Film(1L, "Different Film", "Test Rendező", 2023, 120, "Akció", 8.5);
        Film filmDiffRendezo = new Film(1L, "Test Film", "Different Rendező", 2023, 120, "Akció", 8.5);
        Film filmDiffEv = new Film(1L, "Test Film", "Test Rendező", 2024, 120, "Akció", 8.5);
        Film filmDiffHossz = new Film(1L, "Test Film", "Test Rendező", 2023, 130, "Akció", 8.5);
        Film filmDiffKategoria = new Film(1L, "Test Film", "Test Rendező", 2023, 120, "Different", 8.5);
        Film filmDiffErtekeles = new Film(1L, "Test Film", "Test Rendező", 2023, 120, "Akció", 9.0);

        assertNotEquals(film1, filmDiffId);
        assertNotEquals(film1, filmDiffCim);
        assertNotEquals(film1, filmDiffRendezo);
        assertNotEquals(film1, filmDiffEv);
        assertNotEquals(film1, filmDiffHossz);
        assertNotEquals(film1, filmDiffKategoria);
        assertNotEquals(film1, filmDiffErtekeles);

        // Test hashCode
        assertEquals(film1.hashCode(), film2.hashCode()); // Equal objects should have equal hash codes
        assertNotEquals(film1.hashCode(), film3.hashCode()); // Different objects may have different hash codes

        // Test null field handling
        Film filmWithNulls = new Film(1L, null, null, 2023, 120, null, 8.5);
        Film anotherFilmWithNulls = new Film(1L, null, null, 2023, 120, null, 8.5);
        assertEquals(filmWithNulls, anotherFilmWithNulls); // Objects with null fields should be equal if all fields match
        assertEquals(filmWithNulls.hashCode(), anotherFilmWithNulls.hashCode()); // Hash codes should match for equal objects with null fields
    }

    @Test
    void testNoArgsConstructor() {
        // Act
        Film film = new Film();

        // Assert
        assertNull(film.getId());
        assertNull(film.getCim());
        assertNull(film.getRendezo());
        assertEquals(0, film.getEv());
        assertEquals(0, film.getHossz());
        assertNull(film.getKategoria());
        assertEquals(0.0, film.getErtekeles());
    }

    @Test
    void testToString() {
        // Arrange
        Film film = new Film(1L, "Test Film", "Test Rendező", 2023, 120, "Akció", 8.5);

        // Act
        String result = film.toString();

        // Assert
        assertTrue(result.contains("Test Film"));
        assertTrue(result.contains("Test Rendező"));
        assertTrue(result.contains("2023"));
        assertTrue(result.contains("120"));
        assertTrue(result.contains("Akció"));
        assertTrue(result.contains("8.5"));
    }
}
