package test.whitebox;

import static org.junit.Assert.*;
import org.junit.Test;
import com.filmrecommendation.models.Film;
import com.filmrecommendation.service.FilmService;

public class FilmServiceStatementTest {
	
	@Test
    public void testGetAllFilms() {
        FilmService filmService = new FilmService();
        assertNotNull(filmService.getAllFilms());
    }


}
