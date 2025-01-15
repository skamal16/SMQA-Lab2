package test.whitebox;

import static org.junit.Assert.*;
import org.junit.Test;
import com.filmrecommendation.models.Film;
import com.filmrecommendation.service.FilmService;

public class FilmServiceBranchTest {
	
	@Test
    public void testSearchFilms() {
        FilmService filmService = new FilmService();
        Film film1 = new Film("Inception", "Sci-Fi", 2010, 8.8);
        Film film2 = new Film("Interstellar", "Sci-Fi", 2014, 8.6);
        filmService.addFilm(film1);
        filmService.addFilm(film2);

        // Test branch where title matches
        assertTrue(filmService.searchFilms("Inception").contains(film1));
        assertFalse(filmService.searchFilms("Inception").contains(film2));

        // Test branch where title does not match
        assertTrue(filmService.searchFilms("Interstellar").contains(film2));
        assertFalse(filmService.searchFilms("Interstellar").contains(film1));

        // Test branch where no films match
        assertTrue(filmService.searchFilms("NonExistentTitle").isEmpty());
    }

    @Test
    public void testUpdateFilm() {
        FilmService filmService = new FilmService();
        Film film = new Film("Inception", "Sci-Fi", 2010, 8.8);
        filmService.addFilm(film);
        Film updatedFilm = new Film("Inception", "Action", 2010, 9.0);

        // Test branch where film is found and updated
        boolean result = filmService.updateFilm("Inception", updatedFilm);
        assertTrue(result);
        assertTrue(filmService.searchFilms("Inception").contains(updatedFilm));

        // Test branch where film is not found
        result = filmService.updateFilm("NonExistentTitle", updatedFilm);
        assertFalse(result);
    }

    @Test
    public void testDeleteFilm() {
        FilmService filmService = new FilmService();
        Film film = new Film("Inception", "Sci-Fi", 2010, 8.8);
        filmService.addFilm(film);

        // Test branch where film is found and deleted
        boolean result = filmService.deleteFilm("Inception");
        assertTrue(result);
        assertFalse(filmService.getAllFilms().contains(film));

        // Test branch where film is not found
        result = filmService.deleteFilm("NonExistentTitle");
        assertFalse(result);
    }

}
