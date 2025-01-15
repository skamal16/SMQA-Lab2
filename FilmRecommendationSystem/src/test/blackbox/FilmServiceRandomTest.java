package test.blackbox;

import static org.junit.Assert.*;
import org.junit.Test;
import com.filmrecommendation.models.Film;
import com.filmrecommendation.service.FilmService;
import java.util.Random;

public class FilmServiceRandomTest {
	
	@Test
    public void testAddRandomFilm() {
        FilmService filmService = new FilmService();
        Random random = new Random();
        Film film = new Film("RandomTitle" + random.nextInt(1000), "Genre" + random.nextInt(10), 2000 + random.nextInt(21), random.nextDouble() * 10);
        filmService.addFilm(film);
        assertTrue(filmService.getAllFilms().contains(film));
    }

    @Test
    public void testSearchRandomFilm() {
        FilmService filmService = new FilmService();
        Random random = new Random();
        String title = "RandomTitle" + random.nextInt(1000);
        Film film = new Film(title, "Genre" + random.nextInt(10), 2000 + random.nextInt(21), random.nextDouble() * 10);
        filmService.addFilm(film);
        assertTrue(filmService.searchFilms(title).contains(film));
    }

    @Test
    public void testUpdateRandomFilm() {
        FilmService filmService = new FilmService();
        Random random = new Random();
        String title = "RandomTitle" + random.nextInt(1000);
        Film film = new Film(title, "Genre" + random.nextInt(10), 2000 + random.nextInt(21), random.nextDouble() * 10);
        filmService.addFilm(film);
        Film updatedFilm = new Film(title, "UpdatedGenre" + random.nextInt(10), 2000 + random.nextInt(21), random.nextDouble() * 10);
        boolean result = filmService.updateFilm(title, updatedFilm);
        assertTrue(result);
        assertTrue(filmService.searchFilms(title).contains(updatedFilm));
    }

    @Test
    public void testDeleteRandomFilm() {
        FilmService filmService = new FilmService();
        Random random = new Random();
        String title = "RandomTitle" + random.nextInt(1000);
        Film film = new Film(title, "Genre" + random.nextInt(10), 2000 + random.nextInt(21), random.nextDouble() * 10);
        filmService.addFilm(film);
        boolean result = filmService.deleteFilm(title);
        assertTrue(result);
        assertFalse(filmService.getAllFilms().contains(film));
    }

}
