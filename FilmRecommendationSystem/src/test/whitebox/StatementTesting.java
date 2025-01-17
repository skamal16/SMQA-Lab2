package test.whitebox;

import com.filmrecommendation.models.Film;
import com.filmrecommendation.service.FilmService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * ===========================================================================================
 *                                 Film Recommendation System
 *                                MSc Advanced Computer Science
 *                                  University of Leicester
 * 
 *  Module: Software Measurement and Quality Assurance (CO7095)
 *  Last Modified: 17/01/2025
 * 
 *  This class is part of the group project for the Film Recommendation System.
 *  The project aims to provide personalized film recommendations using various algorithms 
 *  and user preferences. This system incorporates film data management, user authentication, 
 *  and an intuitive interface to enhance the overall user experience.
 * 
 * ===========================================================================================
 */

public class StatementTesting {

    private FilmService filmService = new FilmService();

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
        assertTrue(filmService.getAllFilms().size() > 1, "Multiple films should be added successfully");
    }

    @Test
    public void testGetAllFilms() {
        filmService.addFilm(new Film("Film1", "Genre1", 2020, 7.5));
        filmService.addFilm(new Film("Film2", "Genre2", 2021, 8.0));

        assertNotNull(filmService.getAllFilms(), "Film list should not be null");
        assertTrue(filmService.getAllFilms().size() > 0, "Film list should contain films");
    }

    @Test
    public void testAddFilmWithMissingTitle() {
        Film film = new Film("", "Action", 2020, 7.5);
        filmService.addFilm(film);
        assertFalse(filmService.getAllFilms().contains(film), "Film with missing title should not be added");
    }

    @Test
    public void testDeleteFilm() {
        Film film = new Film("The Matrix", "Sci-Fi", 1999, 8.8);
        filmService.addFilm(film);
        boolean result = filmService.deleteFilm("The Matrix");
        assertTrue(result, "Film should be deleted successfully");
    }

    @Test
    public void testDeleteNonExistentFilm() {
        boolean result = filmService.deleteFilm("NonExistent Film");
        assertFalse(result, "Deleting a non-existent film should return false");
    }

    @Test
    public void testSearchFilmByTitle() {
        Film film = new Film("Inception", "Sci-Fi", 2010, 8.8);
        filmService.addFilm(film);
        assertTrue(filmService.searchFilms("Inception").size() > 0, "Film should be found by title");
    }

    @Test
    public void testSearchFilmByNonExistentTitle() {
        assertTrue(filmService.searchFilms("NonExistent Film").isEmpty(), "Search should return empty for nonexistent films");
    }

    @Test
    public void testSearchFilmByTitleCaseInsensitive() {
        Film film = new Film("The Dark Knight", "Action", 2008, 9.0);
        filmService.addFilm(film);
        assertTrue(filmService.searchFilms("the dark knight").size() > 0, "Search should be case-insensitive");
    }

    @Test
    public void testSearchFilmWithSpecialCharacters() {
        Film film = new Film("S!per M@n", "Action", 2010, 8.8);
        filmService.addFilm(film);
        assertTrue(filmService.searchFilms("S!per M@n").size() > 0, "Film with special characters should be found");
    }

}