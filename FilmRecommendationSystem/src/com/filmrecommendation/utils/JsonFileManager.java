package com.filmrecommendation.utils;

import com.filmrecommendation.models.Film;
import com.filmrecommendation.models.User;
import com.filmrecommendation.models.Admin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
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

public class JsonFileManager {
	
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILMS_FILE = "data/films.json";
    private static final String USERS_FILE = "data/users.json";
    private static final String ADMINS_FILE = "data/admins.json";

    /**
     * Loads the list of films from the JSON file.
     * It calls the generic loadFromFile method with the specific type for films.
     * @return a list of films loaded from the file
     */
    public static List<Film> loadFilms() {
        return loadFromFile(FILMS_FILE, new TypeToken<ArrayList<Film>>() {}.getType());
    }

    /**
     * Saves the list of films to the JSON file.
     * It calls the generic saveToFile method with the file path for films.
     * @param films the list of films to save
     */
    public static void saveFilms(List<Film> films) {
        saveToFile(FILMS_FILE, films);
    }

    /**
     * Loads the list of users from the JSON file.
     * It calls the generic loadFromFile method with the specific type for users.
     * @return a list of users loaded from the file
     */
    public static List<User> loadUsers() {
        return loadFromFile(USERS_FILE, new TypeToken<ArrayList<User>>() {}.getType());
    }

    /**
     * Saves the list of users to the JSON file.
     * It calls the generic saveToFile method with the file path for users.
     * @param users the list of users to save
     */
    public static void saveUsers(List<User> users) {
        saveToFile(USERS_FILE, users);
    }

    /**
     * Loads the list of admins from the JSON file.
     * It calls the generic loadFromFile method with the specific type for admins.
     * @return a list of admins loaded from the file
     */
    public static List<Admin> loadAdmins() {
        return loadFromFile(ADMINS_FILE, new TypeToken<ArrayList<Admin>>() {}.getType());
    }

    /**
     * Saves the list of admins to the JSON file.
     * It calls the generic saveToFile method with the file path for admins.
     * @param admins the list of admins to save
     */
    public static void saveAdmins(List<Admin> admins) {
        saveToFile(ADMINS_FILE, admins);
    }

    /**
     * A generic method to load data from a given JSON file.
     * It uses Gson to read the file and convert its contents into the specified type.
     * @param filePath the path of the JSON file to read from
     * @param type the type of the data to load (e.g., List<Film>, List<User>)
     * @param <T> the type of the data to load
     * @return a list of data of type T loaded from the file
     */
    private static <T> List<T> loadFromFile(String filePath, Type type) {
        try (Reader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
            return new ArrayList<>();
        }
    }

    /**
     * A generic method to save a list of data to a given JSON file.
     * It uses Gson to convert the data into JSON format and writes it to the file.
     * @param filePath the path of the JSON file to write to
     * @param data the list of data to save
     * @param <T> the type of the data to save
     */
    private static <T> void saveToFile(String filePath, List<T> data) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
        }
    }
    
}