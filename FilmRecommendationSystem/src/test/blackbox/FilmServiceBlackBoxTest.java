package test.blackbox;

import static org.junit.Assert.*;
import org.junit.Test;
import com.filmrecommendation.models.Film;
import com.filmrecommendation.service.FilmService;

public class FilmServiceBlackBoxTest {
	
	@Test
    public void testAddFilm() {
        FilmService filmService = new FilmService();
        Film film = new Film("Inception", "Sci-Fi", 2010, 8.8);
        filmService.addFilm(film);
        assertTrue(filmService.getAllFilms().contains(film));
    }

    @Test
    public void testGetAllFilms() {
        FilmService filmService = new FilmService();
        assertNotNull(filmService.getAllFilms());
    }

    @Test
    public void testSearchFilms() {
        FilmService filmService = new FilmService();
        Film film = new Film("Inception", "Sci-Fi", 2010, 8.8);
        filmService.addFilm(film);
        assertTrue(filmService.searchFilms("Inception").contains(film));
        assertFalse(filmService.searchFilms("NonExistentTitle").contains(film));
    }

    @Test
    public void testUpdateFilm() {
        FilmService filmService = new FilmService();
        Film film = new Film("Inception", "Sci-Fi", 2010, 8.8);
        filmService.addFilm(film);
        Film updatedFilm = new Film("Inception", "Action", 2010, 9.0);
        boolean result = filmService.updateFilm("Inception", updatedFilm);
        assertTrue(result);
        assertTrue(filmService.searchFilms("Inception").contains(updatedFilm));
    }

    @Test
    public void testDeleteFilm() {
        FilmService filmService = new FilmService();
        Film film = new Film("Inception", "Sci-Fi", 2010, 8.8);
        filmService.addFilm(film);
        boolean result = filmService.deleteFilm("Inception");
        assertTrue(result);
        assertFalse(filmService.getAllFilms().contains(film));
    }

}
