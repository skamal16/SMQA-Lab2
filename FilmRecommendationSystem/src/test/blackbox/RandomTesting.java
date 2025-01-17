package test.blackbox;

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

public class RandomTesting {

    private FilmService filmService = new FilmService();

    @Test
    public void testAddFilmWithNullTitle() {
        Film film = new Film(null, "Comedy", 2020, 7.5); // Null title
        filmService.addFilm(film);
        assertFalse(filmService.getAllFilms().contains(film), "Film with null title should not be added");
    }

    @Test
    public void testAddFilmWithEmptyTitle() {
        Film film = new Film("", "Action", 2022, 8.5); // Empty title
        filmService.addFilm(film);
        assertFalse(filmService.getAllFilms().contains(film), "Film with empty title should not be added");
    }

    @Test
    public void testAddFilmWithSpecialCharacters() {
        Film film = new Film("S!per M@n", "Action", 2010, 8.8);
        filmService.addFilm(film);
        assertTrue(filmService.searchFilms("S!per M@n").size() > 0, "Film with special characters should be added and found");
    }

    @Test
    public void testAddFilmWithLongTitle() {
        String longTitle = "A very long title that exceeds the normal title length for testing purposes, more than usual";
        Film film = new Film(longTitle, "Drama", 2015, 7.5);
        filmService.addFilm(film);
        assertTrue(filmService.searchFilms(longTitle).size() > 0, "Film with long title should be added and found");
    }

    @Test
    public void testAddFilmWithNonExistentGenre() {
        Film film = new Film("Uncharted", "Fantasy", 2022, 8.0); // Assuming "Fantasy" is not a recognized genre
        filmService.addFilm(film);
        assertTrue(filmService.searchFilms("Uncharted").size() > 0, "Film with a non-existent genre should be added and found");
    }

    @Test
    public void testAddFilmWithFutureReleaseYear() {
        Film film = new Film("Future Movie", "Sci-Fi", 2025, 8.2);
        filmService.addFilm(film);
        assertTrue(filmService.searchFilms("Future Movie").size() > 0, "Film with a future release year should be added and found");
    }

    @Test
    public void testSearchFilmByExactTitle() {
        Film film = new Film("Inception", "Sci-Fi", 2010, 8.8);
        filmService.addFilm(film);
        assertNotNull(filmService.searchFilms("Inception"), "Film should be found by exact title");
    }

    @Test
    public void testSearchFilmWithDifferentCasing() {
        Film film = new Film("The Dark Knight", "Action", 2008, 8.8);
        filmService.addFilm(film);
        assertTrue(filmService.searchFilms("the dark knight").size() > 0, "Search should be case-insensitive");
    }

    @Test
    public void testSearchForFilmNotAdded() {
        assertTrue(filmService.searchFilms("Film Not Added").isEmpty(), "Searching for a non-added film should return an empty result");
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
    public void testDeleteNonExistentFilm() {
        boolean isDeleted = filmService.deleteFilm("NonExistentFilm");
        assertFalse(isDeleted, "Attempting to delete a non-existent film should return false");
    }

}