package com.filmrecommendation.service;

import com.filmrecommendation.models.Film;
import com.filmrecommendation.utils.JsonFileManager;

import java.util.ArrayList;
import java.util.List;

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

public class FilmService {

    private List<Film> films;

    public FilmService() {
        this.films = JsonFileManager.loadFilms();
    }

    /**
     * Returns the list of all films available.
     * @return the list of films
     */
    public List<Film> getAllFilms() {
        return films;
    }

    /**
     * Adds a new film to the list if it has a valid title and genre.
     * After adding the film, the list of films is saved to the file.
     * @param film the film to be added
     */
    public void addFilm(Film film) {
        if (film.getTitle() == null || film.getTitle().isEmpty() || film.getGenre() == null || film.getGenre().isEmpty()) {
            return;
        }
        films.add(film);
        JsonFileManager.saveFilms(films);
    }

    /**
     * Updates an existing film's details. The film is located by its title, 
     * and if a match is found, it is updated with the provided details.
     * The updated list is then saved to the file.
     * @param title the title of the film to be updated
     * @param updatedFilm the updated film object
     * @return true if the film was updated successfully, false otherwise
     */
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

    /**
     * Deletes a film from the list based on its title. If the film is found and removed,
     * the updated list is saved to the file.
     * @param title the title of the film to be deleted
     * @return true if the film was removed, false otherwise
     */
    public boolean deleteFilm(String title) {
    	for (int i = 0; i < films.size(); i++) {
            if (films.get(i).getTitle().equals(title)) {
                films.remove(i);
                JsonFileManager.saveFilms(films);
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for films by title. If films matching the title are found, they are added to the result list.
     * @param title the title to search for
     * @return a list of films that match the given title
     */
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