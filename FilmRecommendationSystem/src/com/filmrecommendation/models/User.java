package com.filmrecommendation.models;

import java.util.ArrayList;
import java.util.List;

public class User {
	
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private List<Film> savedFilms;

    public User(String firstName, String lastName, String dateOfBirth, String username, String password) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.savedFilms = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public List<Film> getSavedFilms() {
        return savedFilms;
    }

    public void addSavedFilm(Film film) {
        savedFilms.add(film);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}

