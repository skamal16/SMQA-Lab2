package com.filmrecommendation.service;

import com.filmrecommendation.models.Film;
import com.filmrecommendation.utils.JsonFileManager;

import java.util.ArrayList;
import java.util.List;

public class FilmService {

    private List<Film> films;

    public FilmService() {
        this.films = JsonFileManager.loadFilms();
    }

    public List<Film> getAllFilms() {
        return films;
    }

    public void addFilm(Film film) {
        if (film.getTitle() == null || film.getTitle().isEmpty() || film.getGenre() == null || film.getGenre().isEmpty()) {
            return;
        }
        films.add(film);
        JsonFileManager.saveFilms(films);
    }

    public boolean updateFilm(String title, Film updatedFilm) {
        for (int i = 0; i < films.size(); i++) {
            if (films.get(i).getTitle().equalsIgnoreCase(title)) {
                films.set(i, updatedFilm);
                JsonFileManager.saveFilms(films);
                return true;
            }
        }
        return false;
    }

    public boolean deleteFilm(String title) {
        boolean removed = films.removeIf(film -> film.getTitle().equalsIgnoreCase(title));
        if (removed) {
            JsonFileManager.saveFilms(films);
        }
        return removed;
    }

    public List<Film> searchFilms(String title) {
        List<Film> result = new ArrayList<>();
        for (Film film : films) {
            if (film.getTitle().equalsIgnoreCase(title)) {
                result.add(film);
            }
        }
        return result;
    }

}