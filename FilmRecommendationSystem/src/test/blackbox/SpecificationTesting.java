package test.blackbox;

import com.filmrecommendation.models.Film;
import com.filmrecommendation.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpecificationTesting {

    private FilmService filmService;

    @BeforeEach
    public void setUp() {
        filmService = new FilmService();
    }

    @Test
    public void testAddFilm() {
        Film film = new Film("The Matrix", "Sci-Fi", 1999, 8.8);
        filmService.addFilm(film);
        assertTrue(filmService.getAllFilms().contains(film), "Film should be added to the list");
    }

    @Test
    public void testAddMultipleFilms() {
        Film film1 = new Film("Titanic", "Romance", 1997, 8.8);
        Film film2 = new Film("Avatar", "Sci-Fi", 2009, 8.8);
        filmService.addFilm(film1);
        filmService.addFilm(film2);
        assertEquals(2, filmService.getAllFilms().size(), "There should be two films in the list");
    }

    @Test
    public void testUpdateFilm() {
        Film oldFilm = new Film("The Matrix", "Sci-Fi", 1999, 8.8);
        filmService.addFilm(oldFilm);
        Film updatedFilm = new Film("The Matrix Reloaded", "Sci-Fi", 2003, 8.8);
        filmService.updateFilm("The Matrix", updatedFilm);
        assertNotNull(filmService.searchFilms("The Matrix Reloaded"), "Film should be updated successfully");
    }
    
    @Test
    public void testGetAllFilms() {
        assertNotNull(filmService.getAllFilms(), "Film list should not be null");
    }

    @Test
    public void testDeleteNonExistentFilm() {
        boolean result = filmService.deleteFilm("Nonexistent Film");
        assertFalse(result, "Deleting non-existent film should return false");
    }

    @Test
    public void testSearchFilmByExactTitle() {
        Film film = new Film("Inception", "Sci-Fi", 2010, 8.8);
        filmService.addFilm(film);
        assertNotNull(filmService.searchFilms("Inception"), "Film should be found by exact title");
    }

    @Test
    public void testSearchFilmNoResults() {
        assertTrue(filmService.searchFilms("Nonexistent Film").isEmpty(), "Search should return empty for nonexistent films");
    }

    @Test
    public void testSearchFilmWithDifferentCasing() {
        Film film = new Film("The Dark Knight", "Action", 2008, 8.8);
        filmService.addFilm(film);
        assertTrue(filmService.searchFilms("the dark knight").size() > 0, "Search should be case-insensitive");
    }

    @Test
    public void testFilmWithSpecialCharacters() {
        Film film = new Film("S!per M@n", "Action", 2010, 8.8);
        filmService.addFilm(film);
        assertTrue(filmService.searchFilms("S!per M@n").size() > 0, "Film with special characters should be found");
    }

    @Test
    public void testAddFilmWithInvalidData() {
        Film invalidFilm = new Film("", "Action", 2022, 8.8);
        filmService.addFilm(invalidFilm);
        assertFalse(filmService.getAllFilms().contains(invalidFilm), "Film with invalid data should not be added");
    }

}