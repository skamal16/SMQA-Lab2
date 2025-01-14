package com.filmrecommendation.service;

import com.filmrecommendation.models.Admin;
import com.filmrecommendation.models.Film;
import com.filmrecommendation.models.User;
import com.filmrecommendation.utils.JsonFileManager;

import java.util.List;

public class AdminService {
	
    private List<Admin> admins;
    private List<Film> films;
    private List<User> users;

    public AdminService() {
        admins = JsonFileManager.loadAdmins();
        films = JsonFileManager.loadFilms();
        users = JsonFileManager.loadUsers();
    }

    public Admin authenticateAdmin(String username, String password) {
        return admins.stream()
                .filter(admin -> admin.getUsername().equalsIgnoreCase(username) && admin.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public List<Admin> getAllAdmins() {
        return admins;
    }

    public void addFilm(Film film) {
        films.add(film);
        JsonFileManager.saveFilms(films);
    }

    public Film searchFilmByTitle(String title) {
        return films.stream().filter(film -> film.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
    }

    public void updateFilm(Film film) {
        JsonFileManager.saveFilms(films);
    }
    
    public boolean deleteFilm(String title) {
        boolean removed = films.removeIf(film -> film.getTitle().equalsIgnoreCase(title));
        if (removed) {
            JsonFileManager.saveFilms(films);
        }
        return removed;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User searchUserByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equalsIgnoreCase(username)).findFirst().orElse(null);
    }

    public void updateUser(User user) {
        JsonFileManager.saveUsers(users);
    }

    public boolean deleteUser(String username) {
        boolean removed = users.removeIf(user -> user.getUsername().equalsIgnoreCase(username));
        if (removed) {
            JsonFileManager.saveUsers(users);
        }
        return removed;
    }
    
}