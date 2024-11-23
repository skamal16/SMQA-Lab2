package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import models.Film;

public class FilmService {
	
    private List<Film> films;

    public FilmService() {
        films = new ArrayList<Film>();
    }

    public List<Film> getAllFilms() {
        return films;
    }

    //https://stackoverflow.com/questions/122105/how-to-filter-a-java-collection-based-on-predicate
    public List<Film> searchFilms(String title) {
        return films.stream().filter(f -> f.getTitle().equalsIgnoreCase(title)).collect(Collectors.toList());
    }

    public void addFilm(Film film) {
        films.add(film);
    }

    public boolean deleteFilm(String title) {
        boolean removed = films.removeIf(film -> film.getTitle().equalsIgnoreCase(title));
        return removed;
    }
    
}