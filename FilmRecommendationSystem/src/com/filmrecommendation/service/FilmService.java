package com.filmrecommendation.service;

import com.filmrecommendation.models.Film;
import com.filmrecommendation.utils.JsonFileManager;

import java.util.List;
import java.util.stream.Collectors;

public class FilmService {
	
    private List<Film> films;

    public FilmService() {
        films = JsonFileManager.loadFilms();
    }

    public List<Film> getAllFilms() {
        return films;
    }

    public List<Film> searchFilms(String title) {
        return films.stream()
                .filter(film -> film.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void addFilm(Film film) {
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
    
}