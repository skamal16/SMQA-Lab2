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

public class BranchTesting {

    private FilmService filmService = new FilmService();

    @Test
    public void testAddFilm() {
        Film film = new Film("Inception", "Sci-Fi", 2010, 8.8);
        filmService.addFilm(film);
        assertTrue(filmService.searchFilms("Inception").size() > 0, "Film should be added successfully");
    }

    @Test
    public void testAddMultipleFilms() {
        Film film1 = new Film("Film1", "Action", 2000, 8.0);
        Film film2 = new Film("Film2", "Drama", 2005, 7.5);
        filmService.addFilm(film1);
        filmService.addFilm(film2);
        assertTrue(filmService.getAllFilms().size() > 1, "Multiple films should be added successfully");
    }

    @Test
    public void testAddFilmWithValidRating() {
        Film film = new Film("Avatar", "Sci-Fi", 2009, 8.8);
        filmService.addFilm(film);
        assertTrue(filmService.searchFilms("Avatar").size() > 0, "Film with valid rating should be added");
    }

    @Test
    public void testAddFilmWithFutureYear() {
        Film film = new Film("Future Film", "Sci-Fi", 2025, 8.2);
        filmService.addFilm(film);
        assertTrue(filmService.searchFilms("Future Film").size() > 0, "Film with a future year should be added");
    }

    @Test
    public void testAddFilmWithCurrentYear() {
        int currentYear = 2025;
        Film film = new Film("Current Year Film", "Drama", currentYear, 7.8);
        filmService.addFilm(film);
        assertTrue(filmService.searchFilms("Current Year Film").size() > 0, "Film with current year should be added");
    }

    @Test
    public void testAddFilmWithNullTitle() {
        Film film = new Film(null, "Action", 2022, 7.5);
        filmService.addFilm(film);
        assertFalse(filmService.getAllFilms().contains(film), "Film with null title should not be added");
    }

    @Test
    public void testAddFilmWithEmptyTitle() {
        Film film = new Film("", "Action", 2022, 7.5);
        filmService.addFilm(film);
        assertFalse(filmService.getAllFilms().contains(film), "Film with empty title should not be added");
    }

    @Test
    public void testSearchFilmExists() {
        Film film = new Film("The Dark Knight", "Action", 2008, 9.0);
        filmService.addFilm(film);
        assertNotNull(filmService.searchFilms("The Dark Knight"), "Film should be found");
    }

    @Test
    public void testSearchFilmNotExist() {
        assertTrue(filmService.searchFilms("NonExistent Film").isEmpty(), "Film should not be found");
    }

    @Test
    public void testDeleteNonExistentFilm() {
        assertFalse(filmService.deleteFilm("NonExistentFilm"), "Attempting to delete a non-existent film should return false");
    }

}