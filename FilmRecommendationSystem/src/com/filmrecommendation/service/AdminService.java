package com.filmrecommendation.service;

import com.filmrecommendation.models.Admin;
import com.filmrecommendation.models.Film;
import com.filmrecommendation.models.User;
import com.filmrecommendation.utils.JsonFileManager;

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

public class AdminService {
	
    private List<Admin> admins;
    private List<Film> films;
    private List<User> users;

    public AdminService() {
        admins = JsonFileManager.loadAdmins();
        films = JsonFileManager.loadFilms();
        users = JsonFileManager.loadUsers();
    }

    /**
     * Authenticates the admin by comparing the given username and password
     * with the list of stored admins. If a matching admin is found, returns the Admin object.
     * Otherwise, returns null.
     * @param username the username of the admin
     * @param password the password of the admin
     * @return the authenticated Admin object or null if authentication fails
     */
    public Admin authenticateAdmin(String username, String password) {
        return admins.stream()
                .filter(admin -> admin.getUsername().equalsIgnoreCase(username) && admin.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the list of all admins.
     * @return the list of all admins
     */
    public List<Admin> getAllAdmins() {
        return admins;
    }

    /**
     * Adds a new film to the list of films and saves the updated list to a file.
     * @param film the film to be added
     */
    public void addFilm(Film film) {
        films.add(film);
        JsonFileManager.saveFilms(films);
    }

    /**
     * Searches for a film by its title in the list of films.
     * @param title the title of the film to search for
     * @return the film if found, otherwise null
     */
    public Film searchFilmByTitle(String title) {
        return films.stream().filter(film -> film.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
    }

    /**
     * Updates the film information and saves the updated list of films to a file.
     * @param film the film to be updated
     */
    public void updateFilm(Film film) {
        JsonFileManager.saveFilms(films);
    }

    /**
     * Deletes a film from the list by its title. If the film is successfully removed,
     * it saves the updated list of films to a file.
     * @param title the title of the film to delete
     * @return true if the film was removed, false otherwise
     */
    public boolean deleteFilm(String title) {
        boolean removed = films.removeIf(film -> film.getTitle().equalsIgnoreCase(title));
        if (removed) {
            JsonFileManager.saveFilms(films);
        }
        return removed;
    }

    /**
     * Returns the list of all users.
     * @return the list of all users
     */
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * Searches for a user by their username in the list of users.
     * @param username the username of the user to search for
     * @return the user if found, otherwise null
     */
    public User searchUserByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equalsIgnoreCase(username)).findFirst().orElse(null);
    }

    /**
     * Updates the user information and saves the updated list of users to a file.
     * @param user the user to be updated
     */
    public void updateUser(User user) {
        JsonFileManager.saveUsers(users);
    }

    /**
     * Deletes a user from the list by their username. If the user is successfully removed,
     * it saves the updated list of users to a file.
     * @param username the username of the user to delete
     * @return true if the user was removed, false otherwise
     */
    public boolean deleteUser(String username) {
        boolean removed = users.removeIf(user -> user.getUsername().equalsIgnoreCase(username));
        if (removed) {
            JsonFileManager.saveUsers(users);
        }
        return removed;
    }
    
}